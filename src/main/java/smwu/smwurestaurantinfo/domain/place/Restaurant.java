package smwu.smwurestaurantinfo.domain.place;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import smwu.smwurestaurantinfo.domain.Category;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Restaurant implements Place {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    private Category category;


}
