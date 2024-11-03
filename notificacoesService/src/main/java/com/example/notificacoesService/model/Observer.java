package com.example.notificacoesService.model;

public interface Observer {
    void update(Object o,NotificationCallback callback,String type);
}
