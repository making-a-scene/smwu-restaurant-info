package smwu.smwurestaurantinfo.domain.tag;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private TagClassification classification;
    @OneToMany(mappedBy = "tag")
    private List<RestaurantTag> restaurantTags = new ArrayList<>(); // 해당 태그가 N번 이상 달린 식당들 목록

}
