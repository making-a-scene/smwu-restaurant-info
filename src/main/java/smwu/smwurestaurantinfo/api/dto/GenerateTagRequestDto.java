package smwu.smwurestaurantinfo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import smwu.smwurestaurantinfo.domain.tag.Tag;
import smwu.smwurestaurantinfo.domain.tag.TagClassification;

@Data
@AllArgsConstructor
public class GenerateTagRequestDto {
    private String tagName;
    private TagClassification tagClassification;

    public Tag toEntity() {
        return Tag.builder()
                .name(tagName)
                .classification(tagClassification)
                .build();
    }
}
