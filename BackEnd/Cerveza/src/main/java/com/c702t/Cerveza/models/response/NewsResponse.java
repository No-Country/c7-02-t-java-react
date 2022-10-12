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
//    private BusinessEntity business;
    private Long business_id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp timestamp;

}
