package com.tensquare.user.service.impl;

import com.tensquare.user.entity.User;
import com.tensquare.user.repository.UserRepository;
import com.tensquare.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdWorker idWorker;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User login(String mobile, String password) {
        User user = userRepository.findByMobile(mobile);
//        if (user != null && encoder.matches(password, user.getPassword())) {
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Transactional
    @Override
    public void updateFansCountAndFollowCount(int x, String userId, String friendId) {
        userRepository.updateFansCount(x, friendId);
        userRepository.updateFollowCount(x, userId);
    }


    @Override
    public User findByMobileAndPassword(String mobile, String password) {
        User user = userRepository.findByMobile(mobile);
//        if (user != null && encoder.matches(password, user.getPassword())) {
        if (user != null && password.equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        user.setFollowCount(0);
        user.setFansCount(0);
        user.setOnline(0L);
        user.setRegDate(new Date());
        user.setUpdateDate(new Date());
        user.setLastDate(new Date());
        redisTemplate.delete("cmsCode_" + user.getMobile());
//        user.setPassword(encoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void sendSms(String mobile) {
        // 1. 生成6位短信验证码
        String code = RandomStringUtils.randomNumeric(6);
//        Random random = new Random();
//        int max = 999999;
//        int min = 100000;
//        int code = random.nextInt(max);
//        if (code < min) {
//            code = code + min;
//        }
        System.out.println(mobile + "收到的验证码是：" + code);
        // 2. 将验证码放入Redis, 5分钟过期
        redisTemplate.opsForValue().set("smsCode_" + mobile, code + "", 5, TimeUnit.MINUTES);

        // 3. 将验证码和手机号发送到rabbitMQ中
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code + "");
        rabbitTemplate.convertAndSend("sms", map);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userRepository.findAll(specification);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 手机号码
                if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
                    predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 昵称
                if (searchMap.get("nickName") != null && !"".equals(searchMap.get("nickName"))) {
                    predicateList.add(cb.like(root.get("nickName").as(String.class), "%" + (String) searchMap.get("nickName") + "%"));
                }
                // 性别
                if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
                }
                // 头像
                if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
                    predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
                }
                // E-Mail
                if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
                }
                // 兴趣
                if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
                    predicateList.add(cb.like(root.get("interest").as(String.class), "%" + (String) searchMap.get("interest") + "%"));
                }
                // 个性
                if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
                    predicateList.add(cb.like(root.get("personality").as(String.class), "%" + (String) searchMap.get("personality") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
