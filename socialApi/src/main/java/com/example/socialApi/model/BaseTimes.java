package com.example.socialApi.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
public abstract class BaseTimes {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdBy;

    @LastModifiedDate
    private LocalDateTime modifiedBy;
}
