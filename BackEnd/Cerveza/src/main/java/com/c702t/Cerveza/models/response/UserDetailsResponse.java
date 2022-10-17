package com.c702t.Cerveza.models.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserDetailsResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private Timestamp timestamp;

}