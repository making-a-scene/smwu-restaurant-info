package smwu.smwurestaurantinfo.domain.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import smwu.smwurestaurantinfo.domain.place.Restaurant;

@Entity
@IdClass(RestaurantTagId.class)
@Getter
public class RestaurantTag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    @JsonIgnore
    private Tag tag;

}