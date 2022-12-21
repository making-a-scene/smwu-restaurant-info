package smwu.smwurestaurantinfo.api.dto;

import lombok.Data;
import smwu.smwurestaurantinfo.domain.tag.TagClassification;

@Data
public class UpdateTagRequestDto {
    private Long tagId;

    private String tagName;
    private TagClassification classification;

}
