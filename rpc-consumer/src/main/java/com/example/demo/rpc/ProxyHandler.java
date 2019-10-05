package com.example.demo.rpc;

import com.example.demo.entity.Student;
import com.example.demo.util.ZookListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author M
 */
@Component
public class ProxyHandler implements InvocationHandler {

    @Resource
    private ZookListener zookListener;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String path = zookListener.getPath();
        if (StringUtils.isEmpty(path)) {
            throw new Exception("对不起，产品暂时停止服务！");
        }
        System.out.println(path);
        return restTemplate.getForObject("http://" + zookListener.getPath() + "/provide", Student.class);
    }

}
