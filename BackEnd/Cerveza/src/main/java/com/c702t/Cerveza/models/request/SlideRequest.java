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

<<<<<<< HEAD
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "business_id")
//    private BusinessEntity business;

    private Long business_id;

    private String photo;

=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slide_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "business_id")
    private BusinessEntity business;

    private String photo;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = false;

>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40

}
