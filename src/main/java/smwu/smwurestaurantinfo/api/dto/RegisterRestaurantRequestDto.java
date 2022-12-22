package smwu.smwurestaurantinfo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import smwu.smwurestaurantinfo.domain.place.Restaurant;

@Data
@AllArgsConstructor
public class RegisterRestaurantRequestDto {
    private String name;
    private String address;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .address(address)
                .build();
    }
}
