package com.vlog.controller;

import com.vlog.payLoad.CommentDto;
import com.vlog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//A convenience annotation that is itself annotated with @Controller and @ResponseBody
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable long postId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dtos = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }
    //for git hub push nd pull command used 
    public void test(){
    }
        

}
