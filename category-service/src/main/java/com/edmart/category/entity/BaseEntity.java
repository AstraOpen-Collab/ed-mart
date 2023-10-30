package com.edmart.category.entity;

import lombok.Data;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    @PrePersist
    public  void prePersist(){
        LocalDateTime localDateTime=LocalDateTime.now();
        this.createdAt=localDateTime;
        this.updatedAt=localDateTime;
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt=LocalDateTime.now();
    }
}
