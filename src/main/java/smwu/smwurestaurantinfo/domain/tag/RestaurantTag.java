package smwu.smwurestaurantinfo.domain.tag;

import jakarta.persistence.*;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;
import smwu.smwurestaurantinfo.domain.place.Restaurant;

@Entity
@IdClass(RestaurantTagId.class)
@Getter
public class RestaurantTag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    @JsonIgnore
    private Tag tag;

    private int count; // restaurant에 대해 tag가 선택된 횟수

}
