package smwu.smwurestaurantinfo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name="members")
@Getter
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
}
