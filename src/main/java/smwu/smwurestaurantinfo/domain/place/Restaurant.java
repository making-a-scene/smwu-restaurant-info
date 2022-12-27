package smwu.smwurestaurantinfo.domain.place;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import smwu.smwurestaurantinfo.domain.tag.RestaurantTag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant implements Place {
    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String address;

    private String link;
    private String description;
    private int mapX;
    private int mapY;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTag> restaurantTags = new ArrayList<>(); // n개 이상 선택틀 받은 태그들만 저장

    // 리뷰 목록 저장 필드 생성

}
