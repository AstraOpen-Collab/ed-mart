package com.edmart.category.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    @CreationTimestamp
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize
    @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize
    @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
    private LocalDateTime updatedAt;
}
