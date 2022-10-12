package com.c702t.Cerveza.models.request;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing an Slides Request.")
public class SlideRequest {

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "business_id")
//    private BusinessEntity business;

    private Long business_id;

    private String photo;


}
