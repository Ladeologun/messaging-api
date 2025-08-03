package com.xproj.simpleMessagingApi.model;


import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SharedMessage {

    private String fromSender;
    private String toReceiver;
    private String content;
//    private SharedMessageType type;
//    private LocalDateTime timestamp;
}
