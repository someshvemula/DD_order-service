package com.someshvemula.orderservice.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPlacedEvent {
    private String recipientName;
    private String recipientEmail;
    private String content;
}
