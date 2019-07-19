package com.hosp.hospms.models.domains;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@Data
public abstract class DocumentBase {

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;
    @Version
    private Long version;

    private boolean active = true;
}
