package com.tak.article.domain.article.service;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.exception.NotExistPostException;
import com.tak.article.domain.article.repositoriy.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Post getPost(Long id) {
        Post post = articleRepository.findById(id).orElseThrow(NotExistPostException::new);
        post.incrementViews();
        return post;
    }

    public List<Post> getPostList() {
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void writePost(Post post) {
        articleRepository.save(post);
    }
}
