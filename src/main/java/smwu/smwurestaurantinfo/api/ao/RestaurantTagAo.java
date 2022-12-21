package smwu.smwurestaurantinfo.api.ao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RestaurantTagAo {
    private final Long restaurantId;
    private final Long tagId;
}
