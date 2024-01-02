package com.vlog.service.Impl;

import com.exception.ResourceNotFoundException;
import com.vlog.entity.Comment;
import com.vlog.entity.Post;
import com.vlog.payLoad.CommentDto;
import com.vlog.repository.CommentRepository;
import com.vlog.repository.PostRepository;
import com.vlog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;


    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("post is not found with id :" + postId)
        );
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);

        CommentDto dtos = mapToDto(saveComment);


        return dtos;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
}
