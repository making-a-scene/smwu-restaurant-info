package smwu.smwurestaurantinfo.domain.tag;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTagId implements Serializable {

    @Column(name = "restaurant_id")
    private String restaurant; // RestaurantTag.restaurant와 연결

    @Column(name = "tag_id")
    private String tag; // RestaurantTag.tag와 연결

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}



