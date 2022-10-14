package com.c702t.Cerveza.models.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CommentEntityKey implements Serializable {
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "review_id")
    private Long reviewID;


    public int hashCode() {
        return (int) (this.userID + this.reviewID);
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof CommentEntityKey)) return false;
        CommentEntityKey pk = (CommentEntityKey) obj;
        return pk.userID == this.userID && pk.reviewID == this.reviewID;
    }
}