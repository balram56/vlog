package com.vlog.service;

import com.vlog.payLoad.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId) ;


}
