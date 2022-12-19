package smwu.smwurestaurantinfo.domain;

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

    @OneToMany(mappedBy = "tag")
    private List<RestaurantTag> restaurantTags = new ArrayList<>();

}
