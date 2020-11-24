package com.tensquare.user.controller;

import com.tensquare.user.entity.Admin;
import com.tensquare.user.service.AdminService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 管理员登录
     *
     * @param loginMap loginMap
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginMap) {
        Admin admin = adminService.findByLoginNameAndPassword(loginMap.get("loginName"), loginMap.get("password"));
        if (admin != null) {
            // 生成token
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginName(), "admin");
            Map<Object, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("name", admin.getLoginName());
            return new Result(true, StatusCode.OK, "登录成功", map);
        } else {
            return new Result(true, StatusCode.ACCESSERROR, "用户名或密码错误");
        }
    }

    /**
     * 查询全部数据
     *
     * @return Result
     */
    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", adminService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return rESULT
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", adminService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Admin>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap searchMap
     * @return Result
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", adminService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param admin admin
     */
    @PostMapping()
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param admin admin
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.update(admin);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        adminService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}

