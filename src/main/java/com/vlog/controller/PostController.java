package com.vlog.controller;

import com.vlog.payLoad.PostDto;
import com.vlog.service.PostService;

import javax.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vlog_post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
//For save
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto =postService.createPost(postDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);

    }
    //For delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post is deleted",HttpStatus.OK);

    }
    //for read
    //http://localhost:8080/api/vlog_post?pageNo=0&pageSize=5&sortBy=id
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id", required = false) String sortBy
    ){

        List<PostDto> allPosts = postService.getAllPosts(pageNo,pageSize,sortBy);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }
    //for update
    @PutMapping
    public ResponseEntity<PostDto> updatePosts(
            @RequestParam("postId") long postId,
            @RequestBody PostDto postDto
    ){
        PostDto dto = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
