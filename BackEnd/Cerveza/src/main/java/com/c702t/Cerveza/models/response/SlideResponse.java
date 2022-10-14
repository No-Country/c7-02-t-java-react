package com.c702t.Cerveza.models.response;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SlideResponse {

    private Long id;
<<<<<<< HEAD
//    private BusinessEntity business;
    private Long business_id;
=======
    private BusinessEntity business;
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
    private String photo;
    private Timestamp timestamp;

}
