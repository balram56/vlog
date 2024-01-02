package com.vlog.service;


import com.vlog.payLoad.PostDto;

import java.util.List;

public interface PostService {

  public PostDto createPost(PostDto postDto);

  void deletePost(long id);


  List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy);

  PostDto updatePost(long postId, PostDto postDto);
}
