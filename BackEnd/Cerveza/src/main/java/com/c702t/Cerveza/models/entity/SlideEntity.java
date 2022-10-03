package com.c702t.Cerveza.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "slide")
public class SlideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "The urlImage can't be null")
    @NotBlank(message = "The urlImage can't be blank")
    @Column( name = "url_image", nullable = false)
    private String urlImage;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    /* TODO uncomment when BusinessEntity is created
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessEntity business;

    Move this lines to BusinessEntity -->
    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<newsEntity> slide;
    */



}