package smwu.smwurestaurantinfo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import smwu.smwurestaurantinfo.api.ao.RestaurantInfoAo;
import smwu.smwurestaurantinfo.domain.place.Restaurant;
import smwu.smwurestaurantinfo.service.RestaurantService;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestaurantApiController {
    private final RestaurantService restaurantService;

    private static final String CLIENT_ID = "mOgG_Z0H1TceihUR73Tc";
    private static final String CLIENT_SECRET = "83gOpMyiwB";

    private static String buildRequest(URI uri) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();
        return restTemplate.exchange(req, String.class).getBody();
    }

    private static ResponseEntity<Object> buildResponseEntity(Object o) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(o, httpHeaders, OK);
    }


    // http://localhost:8080/restaurant/search/place-name?value=(value)
    @RequestMapping(value = "/restaurant/search/place-name", method = RequestMethod.POST)
    public String searchRestaurantByTitle(@RequestParam(name = "value") String value) {
        ByteBuffer buffer = UTF_8.encode(value);
        String encode = UTF_8.decode(buffer).toString();
        return buildRequest(
                UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", encode)
                .queryParam("display", 10)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri()
        );
    }

    @RequestMapping(value = "/restaurant/detail", method = RequestMethod.POST)
    public ResponseEntity<Object> getRestaurantAddress(@RequestBody RestaurantInfoAo ao) {
        String address = ao.getAddress();
        String title = ao.getTitle();
        final HttpHeaders headers = new HttpHeaders();
        if (restaurantService.findByAddress(address).isPresent()) {
            // exist
            headers.setLocation(URI.create("/restaurant/detail/exist?id=" + restaurantService.findByAddress(address).get().getId()));
        } else {
            // non-exist
            headers.setLocation(URI.create("/restaurant/search/place-name?value=" + URLEncoder.encode(title, UTF_8)));
        }
        return new ResponseEntity<>(headers, MOVED_PERMANENTLY);
    }
    @RequestMapping(value = "/restaurant/detail/exist", method = RequestMethod.GET)
    public ResponseEntity<Object> showExistingRestaurantDetail(@RequestParam(name = "id") Long id) {
        Restaurant restaurant = restaurantService.findById(id);
        return buildResponseEntity(restaurant);
    }

    // http://localhost:8080/restaurant/search/tags?name=tagNames1&name=tagNames2&name=tagNames3...
    // String[] tagNames = request.getParameterValues("name");
//    @RequestMapping(value = "/search/tags", method = RequestMethod.GET)
//    public void getRestaurantListBySelectedTags(@RequestParam(name = "name") String... name) {
//        // 파라미터로 받은 모든 태그들에 대해 해당 태그를 가지고 있는 식당 목록을 받아오기
//
//    }
}
