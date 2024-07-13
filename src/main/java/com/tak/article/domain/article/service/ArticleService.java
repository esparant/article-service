package com.tak.article.domain.article.service;

import com.tak.article.domain.article.entity.Post;
import com.tak.article.domain.article.exception.NotExistPostException;
import com.tak.article.domain.article.form.PostForm;
import com.tak.article.domain.article.repositoriy.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Post getPost(Long id) {
        return articleRepository.findById(id).orElseThrow(NotExistPostException::new);
    }

    public List<Post> getPostList() {
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public void savePost(Post post) {
        articleRepository.save(post);
    }

    @Transactional
    public void modifyPost(Long id, PostForm form) {
        Post original = articleRepository.findById(id).orElseThrow(NotExistPostException::new);

        original.modifyPost(form);

    }

    @Transactional
    public void deletePost(Long id) {
        Post original = articleRepository.findById(id).orElseThrow(NotExistPostException::new);
        articleRepository.delete(original);
    }

    public Page<Post> searchPost(String title, PageRequest pageRequest) {
        return articleRepository.searchPage(title, pageRequest);
    }
}
