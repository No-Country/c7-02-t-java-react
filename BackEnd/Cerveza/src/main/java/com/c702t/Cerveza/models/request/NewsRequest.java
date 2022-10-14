package com.c702t.Cerveza.models.request;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing an News Request.")
public class NewsRequest {

<<<<<<< HEAD
=======


>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be empty")
    @NotBlank(message = "The name can't be blank")
    @ApiModelProperty(notes = "Name of the News.", example = "weekend promotion", required = true)
    private String name;

    @NotNull(message = "The content can't be null")
    @NotEmpty(message = "The content can't be empty")
    @NotBlank(message = "The content can't be blank")
    @ApiModelProperty(notes = "Content of the News.", example = "promotion 10% discount on red beers until 8pm", required = true)
    private String content;

    @ApiModelProperty(notes = "Photo of the User.")
    private String photo;

<<<<<<< HEAD
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "business_id")
//    private BusinessEntity business;

    private Long business_id;

    @ApiModelProperty(notes = "Start date of the User.", example = "2022-10-10", required = true)
    @Column(name = "start_date")
    private LocalDate startDate;

    @ApiModelProperty(notes = "First name of the User.", example = "2022-10-17", required = true)
    @Column(name = "end_date")
    private LocalDate endDate;

=======
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "business_id")
    private BusinessEntity business;

    @ApiModelProperty(notes = "Start date of the User.", example = "2022-10-01", required = true)
    @Column(name = "start_date")
    private LocalDate startDate;

    @ApiModelProperty(notes = "First name of the User.", example = "2022-10-05", required = true)
    @Column(name = "end_date")
    private LocalDate endDate;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40

}
