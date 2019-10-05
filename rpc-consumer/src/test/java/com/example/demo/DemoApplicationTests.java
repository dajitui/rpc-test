package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.rpc.RpcConsumer;
import com.example.demo.service.DoSomeThingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    RpcConsumer rpcConsumer;

    @Test
    public void contextLoads() {
        DoSomeThingService doSomeThingService= rpcConsumer.getService(DoSomeThingService.class);
        Student result=doSomeThingService.sayHello();
        System.out.println(result);
    }

}
