package com.c702t.Cerveza.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@AllArgsConstructor
@Builder
public class CommentResponse {
    String userName;
    String text;
    Timestamp timestamp;
}
