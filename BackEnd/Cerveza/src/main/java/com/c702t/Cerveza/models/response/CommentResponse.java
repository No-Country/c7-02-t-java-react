package com.c702t.Cerveza.models.response;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    private String userName;
    private String text;
    private Timestamp timestamp;
}
