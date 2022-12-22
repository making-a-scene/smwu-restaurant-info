package smwu.smwurestaurantinfo.api.dto;

import lombok.Builder;
import lombok.Data;
import smwu.smwurestaurantinfo.domain.tag.TagClassification;

@Data
public class UpdateTagRequestDto {

    private Long tagId;
    private String tagName;
    private TagClassification classification;

    @Builder
    public UpdateTagRequestDto(Long tagId, String tagName, TagClassification classification) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.classification = classification;
    }

}
