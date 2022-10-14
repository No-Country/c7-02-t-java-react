package com.c702t.Cerveza.models.response;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsResponse {

    private Long id;
    private String name;
    private String content;
    private String photo;
<<<<<<< HEAD
//    private BusinessEntity business;
    private Long business_id;
=======
    private BusinessEntity business;
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp timestamp;

}
