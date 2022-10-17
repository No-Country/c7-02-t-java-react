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
//    private BusinessEntity business;
    private Long business_id;
    private String photo;

}
