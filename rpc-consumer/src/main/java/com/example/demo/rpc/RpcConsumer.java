package com.example.demo.rpc;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;

@Component
public class RpcConsumer {

    @Resource
    ProxyHandler proxyHandler;

    public <T> T getService(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(RpcConsumer.class.getClassLoader(), new Class<?>[] {clazz}, proxyHandler);
    }

}
