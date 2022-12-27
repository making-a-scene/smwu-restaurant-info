package smwu.smwurestaurantinfo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import smwu.smwurestaurantinfo.domain.place.Restaurant;

@Data
@AllArgsConstructor
public class RegisterRestaurantRequestDto {
    private String title;
    private String address;
    private String link;
    private int mapX;
    private int mapY;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .title(title)
                .address(address)
                .link(link)
                .mapX(mapX)
                .mapY(mapY)
                .build();
    }
}
