package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.util.ZookListener;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ZookListener zookListener;

    @RequestMapping(value = "/provide", method = RequestMethod.GET)
    public Student get() throws Exception {
        //从zookeeper中获取调用的ip
        String path = zookListener.getPath();
        if (StringUtils.isEmpty(path)) {
            throw new Exception("对不起，产品暂时停止服务！");
        }
        System.out.println(path);
        return restTemplate.getForObject("http://" + zookListener.getPath() + "/provide", Student.class);
    }


}
