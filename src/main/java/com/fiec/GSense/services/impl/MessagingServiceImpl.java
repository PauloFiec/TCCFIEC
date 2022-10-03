package com.fiec.GSense.services.impl;

import com.fiec.GSense.models.repositories.UserRepository;
import com.fiec.GSense.models.entities.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void registerFcmToken(User user, String fcmToken) {
        user.setFcmToken(fcmToken);
        userRepository.save(user);
    }

    @Override
    public void sendMessageToUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        String fcmToken = user.getFcmToken();
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        Message message = Message.builder()
                .setToken(fcmToken)
                .putData("dailyMessage", "Oi lindao")
                .build();
        try {
            firebaseMessaging.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
