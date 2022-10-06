package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing an Slides Request.")
public class SlideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slide_id")
    private Long id;

//    @ManyToOne
//    private Business business;

    private String photo;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = false;


}
