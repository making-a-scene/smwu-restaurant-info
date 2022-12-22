package smwu.smwurestaurantinfo.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class RestaurantApiController {

    private final String CLIENT_ID = "mOgG_Z0H1TceihUR73Tc";
    private final String CLIENT_SECRET = "83gOpMyiwB";

    private String buildRequest(URI uri) {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();
        return restTemplate.exchange(req, String.class).getBody();
    }


    // http://localhost:8080/search/place-name?name=(name)
    @RequestMapping(value = "/search/place-name", method = RequestMethod.GET)
    public String searchRestaurantByName(@RequestParam(name="name") String name) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(name);
        String encode = StandardCharsets.UTF_8.decode(buffer).toString();

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

    // http://localhost:8080/search/tags?name=tagNames1&name=tagNames2&name=tagNames3...
    // String[] tagNames = request.getParameterValues("name");
    @RequestMapping(value = "/search/tags", method = RequestMethod.GET)
    public void getRestaurantListBySelectedTags(@RequestParam(name = "name") String... name) {
        // 파라미터로 받은 모든 태그들에 대해 해당 태그를 가지고 있는 식당 목록을 받아오기

    }
}
