package com.anjali.smartproductcatalog.proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {

        // Real Object
        MessageService service = new MessageServiceImpl();

        // Proxy Object
        MessageService proxy = (MessageService) Proxy.newProxyInstance(
                MessageService.class.getClassLoader(),
                new Class[]{MessageService.class},
                new LoggingInvocationHandler(service)
        );

        // Call Method
        proxy.sendMessage("Hello Anjali!");
    }
}