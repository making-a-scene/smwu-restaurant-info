package smwu.smwurestaurantinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smwu.smwurestaurantinfo.api.ao.RestaurantTagAo;
import smwu.smwurestaurantinfo.api.dto.DeleteTagRequestDto;
import smwu.smwurestaurantinfo.api.dto.GenerateTagRequestDto;
import smwu.smwurestaurantinfo.api.dto.UpdateTagRequestDto;
import smwu.smwurestaurantinfo.domain.tag.RestaurantTag;
import smwu.smwurestaurantinfo.domain.tag.Tag;
import smwu.smwurestaurantinfo.repository.TagRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    public Long findTagByName(String name) {
        Tag foundTag = tagRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("해당 태그가 존재하지 않습니다."));
        return foundTag.getId();
    }

    private Tag findTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 태그가 존재하지 않습니다."));
    }

    public int findTaggedNum(RestaurantTagAo restaurantTagAo) {
        Tag foundTag = findTagById(restaurantTagAo.getTagId());
        List<RestaurantTag> restaurantTags = foundTag.getRestaurantTags();
        RestaurantTag restaurantTag =
                restaurantTags.stream()
                .filter((value) -> Objects.equals(value.getRestaurant().getId(), restaurantTagAo.getRestaurantId()))
                .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("해당 식당 정보가 존재하지 않습니다."));
        return restaurantTag.getCount();
    }

    @Transactional
    public Long createNewTag(GenerateTagRequestDto generateTagRequestDto) {
        tagRepository.save(generateTagRequestDto.toEntity());
        return findTagByName(generateTagRequestDto.getTagName());
    }

    @Transactional
    public void deleteTag(DeleteTagRequestDto deleteTagRequestDto) {
        Tag foundTag = findTagById(deleteTagRequestDto.getTagId());
        tagRepository.delete(foundTag);
    }

    @Transactional
    public Long updateTagAttribute(UpdateTagRequestDto updateTagRequestDto) {
        Tag foundTag = findTagById(updateTagRequestDto.getTagId());
        if (!updateTagRequestDto.getTagName().isEmpty()) {
            foundTag.updateTagName(updateTagRequestDto.getTagName());
        }
        if (updateTagRequestDto.getClassification() != null) {
            foundTag.updateTagClassification(updateTagRequestDto.getClassification());
        }
        return foundTag.getId();
    }

}
