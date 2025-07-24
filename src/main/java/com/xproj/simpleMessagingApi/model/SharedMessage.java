package com.xproj.simpleMessagingApi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedMessage {

    private String fromSender;
    private String toReceiver;
    private String content;
    private SharedMessageType type;
    private LocalDateTime timestamp;
}
