package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.CommentEntity;
import com.c702t.Cerveza.models.response.CommentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CommentMapper {
    public CommentResponse toResponse(CommentEntity entity) {
        CommentResponse commentResponse = CommentResponse.builder()
                .userName(entity.getUserEntity().getFirstName() + " " + entity.getUserEntity().getLastName())
                .text(entity.getText())
                .timestamp(entity.getTimestamp())
                .build();
        return commentResponse;
    }

    public List<CommentResponse> toResponseList(Set<CommentEntity> commentEntitySet) {
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (CommentEntity commentEntity :
                commentEntitySet) {
            commentResponseList.add(toResponse(commentEntity));
        }
        return commentResponseList;
    }
}
