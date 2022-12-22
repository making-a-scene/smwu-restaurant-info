package smwu.smwurestaurantinfo.controller;

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
public class SearchApiController {

    private final String CLIENT_ID = "";
    private final String CLIENT_SECRET = "";

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
}
