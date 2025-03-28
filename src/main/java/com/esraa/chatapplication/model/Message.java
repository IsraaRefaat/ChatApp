package com.esraa.chatapplication.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private MessageType type;
    private String content;
    private String sender;

}