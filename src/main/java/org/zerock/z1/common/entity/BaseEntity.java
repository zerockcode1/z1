package org.zerock.z1.common.entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private transient  LocalDateTime regDate;

    @LastModifiedDate
    @Column(name ="moddate")
    private transient LocalDateTime modDate;

}