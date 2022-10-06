package com.c702t.Cerveza.models.response;

import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlideResponse {

    private Long id;
//    private Business business;
    private String photo;
    private Timestamp timestamp;

}
