package smwu.smwurestaurantinfo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTagRequestDto {
    private Long tagId;
}
