package smwu.smwurestaurantinfo.entity.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import smwu.smwurestaurantinfo.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Member(String email) {
        this.email = email;
    }
}
