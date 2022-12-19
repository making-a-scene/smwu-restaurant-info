package smwu.smwurestaurantinfo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import smwu.smwurestaurantinfo.domain.place.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {
    @Id @GeneratedValue
    private Long id;
    private String name;

}
