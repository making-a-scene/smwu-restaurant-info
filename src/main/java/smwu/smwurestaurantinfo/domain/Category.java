package smwu.smwurestaurantinfo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import smwu.smwurestaurantinfo.domain.place.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Restaurant> includedPlaces = new ArrayList<>();
}
