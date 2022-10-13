package com.c702t.Cerveza.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "comment__user_review")
@Entity
public class CommentEntity {
    @EmbeddedId
    CommentEntityKey id;
    @JsonIgnore
    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne
    @MapsId("reviewID")
    @JoinColumn(name = "review_id")
    private ReviewEntity reviewEntity;
    private String text;
    @CreationTimestamp
    private Timestamp timestamp;
}
