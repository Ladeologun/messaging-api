package com.xproj.simpleMessagingApi.controllers;

import com.xproj.simpleMessagingApi.model.SharedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketContoller {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@AuthenticationPrincipal String username, SharedMessage message) {
        simpMessagingTemplate.convertAndSend("/topic/messages", message);
         // simulated delay

    }


}
