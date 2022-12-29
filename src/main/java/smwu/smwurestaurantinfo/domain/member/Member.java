package smwu.smwurestaurantinfo.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import smwu.smwurestaurantinfo.BaseEntity;
import smwu.smwurestaurantinfo.domain.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
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

    @OneToMany(mappedBy = "uploader")
    @JsonIgnore
    private List<Review> uploadedReviews = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Member(String email, Role role, UserStatus status, String nickname) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.status = status;
    }

    public void updateStatus() {
        this.status = UserStatus.ROLE_AUTHENTICATED;
    }

    public void appointMemberToAdmin() {
        updateStatus();
        this.role = Role.ROLE_ADMIN;
    }
}
