package com.c702t.Cerveza.models.response;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessByUserResponse {

    private Long id;

    private String name;

    private String image;

    private String address;

    private String city;

    private String state;

    private String country;

    private Integer phone;

    private String email;

    private String aboutUsText;

    private String urlFacebook;

    private String urlInstagram;

    private Double rating;

    private Long userId;

    Timestamp creationDate;
}
