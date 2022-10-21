package com.c702t.Cerveza.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE business SET soft_delete = true Where id=?")
@Where(clause = "soft_delete=false")
@Table( name= "business")
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String name;

    @NotNull
    @NotEmpty(message = "image can not be null")
    private String image;

    @NotNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String businessAddress;

    @NotNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String businessCity;

    @NotNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String businessState;

    @NotNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String businessCountry;

    private String phone;

    @NotNull
    @NotEmpty(message = "email can not be null")
    @Email(message = "mail is not valid")
    @Column(nullable = false)
    private String email;

    @Lob
    private String aboutUsText;

    @Column(name = "soft_delete")
    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    private Timestamp timestamp;

    private String urlFacebook;

    private String urlInstagram;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity users;

    @JsonIgnore
    @OneToMany(mappedBy = "businessEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ReviewEntity> reviewEntitySet;

    private Float value;


    private Float rating;


    private Integer count;



}

