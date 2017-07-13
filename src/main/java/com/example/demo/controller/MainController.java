package com.example.demo.controller;

import com.example.demo.Bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    MainService mainService;

    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    @Cacheable(value = "count")
    @Transactional(rollbackFor = Exception.class)
    public int redis() {

        int count = mainService.getCount();

        return count;
    }

    @RequestMapping(value = "/1/{id}", method = RequestMethod.GET)
    @Transactional(rollbackFor = Exception.class)
    public UserBean user(@PathVariable String id) {

        UserBean userBean;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey("user/" + id)){

            userBean = (UserBean)valueOperations.get("user/" + id);
            System.out.println("from cache");
        }else{

            userBean = new UserBean();
            userBean.setId(id);
            userBean.setName(id + "name");
            System.out.println("from database");
            valueOperations.set("user/" + id, userBean);
        }

        return userBean;
    }

    @RequestMapping(value = "/2/{id}", method = RequestMethod.GET)
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "indexCache", key="#id")
    public UserBean user2(@PathVariable String id) {

        UserBean userBean = new UserBean();
        userBean.setId(id);
        userBean.setName(id + "name2");

        return userBean;
    }


    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @CacheEvict(value = "indexCache",allEntries=true)
    @Transactional(rollbackFor = Exception.class)
    public void del() {

    }

}
