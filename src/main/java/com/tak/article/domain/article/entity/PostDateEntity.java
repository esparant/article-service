package com.tak.article.domain.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PostDateEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;

    @CreatedDate
    @Column(nullable = false)
    @Setter
    private LocalDateTime updateDate;

    public String formatedCreateDate() {
        return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    public String formatedUpdateDate() {
        return updateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

}
