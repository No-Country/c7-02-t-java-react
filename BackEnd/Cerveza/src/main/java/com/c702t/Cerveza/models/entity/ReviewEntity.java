package com.c702t.Cerveza.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "attentionRate can not be null")
    @Min(value = 1, message = "attentionRate must be between 1 to 5")
    @Max(value = 5, message = "attentionRate must be between 1 to 5")
    private Long attentionRate;

    @NotNull(message = "priceRate can not be null")
    @Min(value = 1, message = "priceRate must be between 1 to 5")
    @Max(value = 5, message = "priceRate must be between 1 to 5")
    private Long priceRate;

    @NotNull(message = "qualityRate can not be null")
    @Min(value = 1, message = "qualityRate must be between 1 to 5")
    @Max(value = 5, message = "qualityRate must be between 1 to 5")
    private Long qualityRate;

    @NotNull(message = "placeRate can not be null")
    @Min(value = 1, message = "placeRate must be between 1 to 5")
    @Max(value = 5, message = "placeRate must be between 1 to 5")
    private Long placeRate;

    @NotNull(message = "totalRate can not be null")
    @Min(value = 1, message = "totalRate must be between 1 to 5")
    @Max(value = 5, message = "totalRate must be between 1 to 5")
    private Float totalRate;

    @NotNull(message = "totalRate can not be null")
    private String text;

    @CreationTimestamp
    private Timestamp timestamp;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity userEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "reviewEntity")
    Set<CommentEntity> commentEntitySet;
    /* Like System
    @ManyToMany(mappedBy = "reviewEntitySetLikes")
    private Set<UserEntity> userEntitySet = new HashSet<>();
    */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="business_id", nullable=false)
    private BusinessEntity businessEntity;
}
