package com.c702t.Cerveza.models.response;

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
//    private BusinessEntity businessEntity;
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp timestamp;

}
