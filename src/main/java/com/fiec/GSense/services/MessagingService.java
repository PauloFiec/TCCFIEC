package com.fiec.GSense.services;


import com.fiec.GSense.models.entities.User;

public interface MessagingService {

    void registerFcmToken(User user, String fcmToken);

    void sendMessageToUser(Integer userId);
}
