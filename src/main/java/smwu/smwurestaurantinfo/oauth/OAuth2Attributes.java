package smwu.smwurestaurantinfo.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import smwu.smwurestaurantinfo.entity.member.Role;

import java.util.Map;
import java.util.NoSuchElementException;

@Getter
@Builder
@RequiredArgsConstructor
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String userNameAttribute;
    private String email;
    private Role role;

    public OAuth2UserInfo of(String provider, String userNameAttribute, Map<String, Object> attributes) {

        if (provider.equals("naver")) {
            OAuth2Attributes oAuth2Attributes = OAuth2Attributes.ofNaver(userNameAttribute, attributes);
            return new NaverUserInfo(oAuth2Attributes.getAttributes());
        }
        throw new NoSuchElementException("해당 로그인 방식은 지원하지 않습니다.");
    }

    private static OAuth2Attributes ofNaver(String userNameAttribute, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attributes.builder()
                .email((String) response.get("email"))
                .userNameAttribute(userNameAttribute)
                .attributes(response)
                .role(Role.ROLE_USER)
                .build();
    }
}
