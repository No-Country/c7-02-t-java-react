package com.c702t.Cerveza.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Entity
@SQLDelete(sql = "UPDATE news SET soft_delete = true Where news_id=?")
@Where(clause = "soft_delete = false")
@Table( name= "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;

    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be empty")
    @NotBlank(message = "The name can't be blank")
    private String name;

    @NotNull(message = "The content can't be null")
    @NotEmpty(message = "The content can't be empty")
    @NotBlank(message = "The content can't be blank")
    private String content;

    private String photo;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "business_id")
//    private BusinessEntity business;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;

}
