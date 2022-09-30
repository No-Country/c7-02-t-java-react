package com.c702t.Cerveza.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@SQLDelete(sql= "UPDATE business SET soft_delete = true WHERE business_id=?")
@Where(clause = "soft_delete=false")
@Table(name = "business")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Long id;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String name;

    @NonNull
    @NotEmpty(message = "image can not be null")
    private String image;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String address;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String city;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String state;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String country;

    private Integer phone;

    @NonNull
    @NotEmpty(message = "email can not be null")
    @Email(message = "mail is not valid")
    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String aboutUsText;

    @Column(name = "soft_delete")
    @Builder.Default
    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    private Timestamp timestamp;

    private String urlFacebook;

    private String urlInstagram;


   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;*/

   /* @JoinColumn(name = "rating_id")
   private RatingEntity rating;*/






}
