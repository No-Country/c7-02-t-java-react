package com.c702t.Cerveza.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE comment__user_review SET soft_delete = true Where comment_Id=?")
@Where(clause = "soft_delete = false")
@Table(name= "comment__user_review")
public class CommentEntity {

    @EmbeddedId
    @Column(name = "comment_Id")
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

    @Column(name = "soft_delete")
    private Boolean sofdelete = Boolean.FALSE;
}
