package smwu.smwurestaurantinfo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import smwu.smwurestaurantinfo.BaseEntity;
import smwu.smwurestaurantinfo.domain.member.Member;

import java.time.LocalDateTime;

@Entity
@Getter
public class Review extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 3000, nullable = false)
    private String content;

    private String orderedMenus;

    @CreatedDate
    private LocalDateTime uploadedAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member uploader;


}
