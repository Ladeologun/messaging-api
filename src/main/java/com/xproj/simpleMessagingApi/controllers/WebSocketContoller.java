package com.xproj.simpleMessagingApi.controllers;

import com.xproj.simpleMessagingApi.model.SharedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WebSocketContoller {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(Authentication authentication, SharedMessage message) {
        System.out.println(">>> [Controller] injected authentication: " + authentication);
        System.out.println("principal.getName() = " + authentication.getName());
//        simpMessagingTemplate.convertAndSend("/topic/messages", message);
        simpMessagingTemplate.convertAndSendToUser(message.getToReceiver(), "/queue/messages", message);
         // simulated delay

    }


}
