package smwu.smwurestaurantinfo.domain.place;

import jakarta.persistence.*;
import lombok.Getter;
import smwu.smwurestaurantinfo.domain.tag.RestaurantTag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "restaurant")
public class Restaurant implements Place {
    @Id @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTag> restaurantTags = new ArrayList<>(); // n개 이상 선택틀 받은 태그들만 저장



}
