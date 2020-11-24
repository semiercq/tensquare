package com.tensquare.user.service.impl;

import com.tensquare.user.entity.Admin;
import com.tensquare.user.repository.AdminRepository;
import com.tensquare.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Admin findByLoginNameAndPassword(String loginName, String password) {
        Admin admin = adminRepository.findByLoginName(loginName);
        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return admin;
        } else {
            return null;
        }
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Page<Admin> findSearch(Map whereMap, int page, int size) {
        Specification<Admin> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return adminRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Admin> findSearch(Map whereMap) {
        Specification<Admin> specification = createSpecification(whereMap);
        return adminRepository.findAll(specification);
    }

    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public void add(Admin admin) {
        admin.setId(idWorker.nextId() + "");
        // 密码加密
        String newPassword = encoder.encode(admin.getPassword());
        admin.setPassword(newPassword);
        adminRepository.save(admin);
    }

    @Override
    public void update(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteById(String id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Specification<Admin> createSpecification(Map searchMap) {

        return new Specification<Admin>() {

            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 登陆名称
                if (searchMap.get("loginName") != null && !"".equals(searchMap.get("loginName"))) {
                    predicateList.add(cb.like(root.get("loginName").as(String.class), "%" + (String) searchMap.get("loginName") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
