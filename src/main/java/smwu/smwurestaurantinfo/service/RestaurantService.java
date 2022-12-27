package smwu.smwurestaurantinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smwu.smwurestaurantinfo.api.dto.RegisterRestaurantRequestDto;
import smwu.smwurestaurantinfo.domain.place.Restaurant;
import smwu.smwurestaurantinfo.domain.tag.RestaurantTag;
import smwu.smwurestaurantinfo.domain.tag.Tag;
import smwu.smwurestaurantinfo.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final TagService tagService;

    public Optional<Restaurant> findByAddress(String address) {
        return restaurantRepository.findByAddress(address);
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("일치하는 식당 정보가 존재하지 않습니다."));
    }

    // 태그; or 조건
    // tagNames 내 태그 중 하나라도 가지고 있는 식당이라면 모두 반환
    public List<Restaurant> getOrListBySelectedTags(String[] tagNames) {
        List<Restaurant> result = new ArrayList<>();
        for (String name : tagNames) {
            Tag tag = tagService.findTagById(tagService.findTagByName(name));
            List<RestaurantTag> restaurantTags = tag.getRestaurantTags();
            restaurantTags.forEach(rt -> result.add(rt.getRestaurant()));
        }
        return result;
    }

    // 태그; and 조건
    // tagNames에 들어 있는 모든 태그들을 가지고 있는 식당들만 반환
    // QueryDsl 사용

    @Transactional
    public Long RegisterNewRestaurant(RegisterRestaurantRequestDto registerRestaurantRequestDto) {
        String address = registerRestaurantRequestDto.getAddress();
        if (findByAddress(address).isPresent()) {
            throw new IllegalArgumentException("이미 등록되어 있는 식당입니다.");
        }
        Restaurant restaurant = registerRestaurantRequestDto.toEntity();
        restaurantRepository.save(registerRestaurantRequestDto.toEntity());
        return restaurant.getId();
    }

}
