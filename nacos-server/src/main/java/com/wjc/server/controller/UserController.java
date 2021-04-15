package com.wjc.server.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjc.server.entity.User;
import com.wjc.server.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wjc
 * @description
 * @date 2020/12/1
 */
//@RefreshScope
@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserProperties userProperties;
//    @Autowired
//    private JobProperties jobProperties;
//
//    @Value("${user.name}")
//    private String name;
//
//    @Autowired
//    private ConfigurableApplicationContext applicationContext;
//    @Autowired
//    private QqqProterties qqqProterties;
//
//    @SentinelResource(value = "hello")
//    @GetMapping(value = "/echo/{string}")
//    public String echo(@PathVariable String string) {
//        System.out.println(qqqProterties.getQqq());
//        System.out.println(userProperties.getName());
//        System.out.println(applicationContext.getEnvironment().getProperty("qqq"));
//        System.out.println(applicationContext.getEnvironment().getProperty("elastic-jobs.jobs"));
//        System.out.println(jobProperties.getJobs().size());
//        System.out.println(name);
//        System.out.println(string);
//        return string;
//    }

    @GetMapping("/user/save")
    public String saveUser(User user){

        userService.save(user);
        System.out.println(user.getId()%6);
        return "success";
    }

    @GetMapping("/user/get")
    public User getUser(Long id){
        return userService.lambdaQuery().eq(User::getId, id).one();
    }

    @GetMapping("/user/list")
    public List<User> list(String name, String phone, Integer cityId, Integer sex){
        return userService.lambdaQuery()
                .like(StringUtils.isNotBlank(name), User::getName, name)
                .eq(StringUtils.isNotBlank(phone), User::getPhone, phone)
                .eq(cityId != null, User::getCityId, cityId)
                .eq(sex != null, User::getSex, sex)
                .orderByDesc(User::getCreateTime)
                .list();
    }

    @GetMapping("/user/page")
    public PageInfo<User> page(Integer pageNum, Long id, String name, String phone, Integer cityId, Integer sex){
        PageHelper.startPage(pageNum, 10);
        List<User> list = userService.lambdaQuery()
                .eq(id != null, User::getId, id)
                .like(StringUtils.isNotBlank(name), User::getName, name)
                .eq(StringUtils.isNotBlank(phone), User::getPhone, phone)
                .eq(cityId != null, User::getCityId, cityId)
                .eq(sex != null, User::getSex, sex)
                .orderByDesc(User::getCreateTime)
                .list();
        return new PageInfo<>(list);
    }

    @GetMapping("/user/update")
    public String update(User user){
        userService.updateById(user);
        return "success";
    }

    @GetMapping("/user/remove")
    public String remove(Long id){
        userService.removeById(id);
        return "success";
    }
}
