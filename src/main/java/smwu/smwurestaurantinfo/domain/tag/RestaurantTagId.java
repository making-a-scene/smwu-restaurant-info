package smwu.smwurestaurantinfo.domain.tag;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class RestaurantTagId implements Serializable {

    private String restaurant; // RestaurantTag.restaurant와 연결
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



