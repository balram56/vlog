package com.vlog.service.Impl;

import com.exception.ResourceNotFoundException;
import com.vlog.entity.Post;
import com.vlog.payLoad.PostDto;
import com.vlog.repository.PostRepository;
import com.vlog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //For saved entity object to db
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setComment(postDto.getComment());

        Post savedPost =postRepo.save(post);
        // after saving
        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setComment(post.getComment());
        dto.setMessage("post is saved");

       return dto;
    }

    @Override
    public void deletePost(long id) {
       Post pst = postRepo.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("post not found with id:" +id)
       );
        postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Post> posts = postRepo.findAll(pageable);
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new RuntimeException("post is not with id: " + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setComment(postDto.getComment());

        Post savePost = postRepo.save(post);
        PostDto dto = mapToDto(savePost);
        return dto;
    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setComment(post.getComment());
        return dto;
    }
}
