package com.anjali.smartproductcatalog.proxy;

public class MessageServiceImpl implements MessageService {

    @Override
    public void sendMessage(String message) {

        System.out.println("Real Service : " + message);

    }
}