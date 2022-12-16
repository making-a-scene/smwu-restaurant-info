package smwu.smwurestaurantinfo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name="members")
@Getter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String email;

    @Column(nullable = false)
    private String actualName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

}
