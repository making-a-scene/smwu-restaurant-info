package smwu.smwurestaurantinfo.domain.place;

import jakarta.persistence.*;
import lombok.Getter;
import smwu.smwurestaurantinfo.domain.tag.RestaurantTag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Restaurant implements Place {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTag> restaurantTags = new ArrayList<>(); // n개 이상 선택틀 받은 태그들만 저장



}
