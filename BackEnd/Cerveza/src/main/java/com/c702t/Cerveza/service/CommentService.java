package com.c702t.Cerveza.service;

import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.CommentEntity;
import com.c702t.Cerveza.models.entity.CommentEntityKey;
import com.c702t.Cerveza.models.entity.ReviewEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.mapper.CommentMapper;
import com.c702t.Cerveza.models.request.CommentRequest;
import com.c702t.Cerveza.models.response.CommentResponse;
import com.c702t.Cerveza.repository.CommentRepository;
import com.c702t.Cerveza.repository.ReviewRepository;
import com.c702t.Cerveza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    JwtUtils jwtUtils;

    public CommentResponse create(String token, Long reviewID, CommentRequest commentRequest) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();
        ReviewEntity reviewEntity = reviewRepository.getById(reviewID);
        if (reviewEntity == null) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + reviewID + " not found.");
        }
        CommentEntity commentEntity = new CommentEntity();
        CommentEntityKey commentEntityKey = new CommentEntityKey();
        commentEntityKey.setUserID(userEntity.getId());
        commentEntityKey.setReviewID(reviewEntity.getId());
        commentEntity.setId(commentEntityKey);
        commentEntity.setUserEntity(userEntity);
        commentEntity.setReviewEntity(reviewEntity);
        commentEntity.setText(commentRequest.getText());
        CommentEntity commentEntitySaved = commentRepository.save(commentEntity);
        return commentMapper.toResponse(commentEntitySaved);
    }

    public List<CommentResponse> readByReviewID(Long reviewID) {
        ReviewEntity reviewEntity = reviewRepository.getById(reviewID);
        if (reviewEntity == null) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + reviewID + " not found.");
        }
        return commentMapper.toResponseList(reviewEntity.getCommentEntitySet());
    }
}
