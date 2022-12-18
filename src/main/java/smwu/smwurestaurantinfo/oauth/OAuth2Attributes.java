package smwu.smwurestaurantinfo.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import smwu.smwurestaurantinfo.entity.member.Role;

import java.util.Map;
import java.util.NoSuchElementException;

@Getter
@Builder
@Slf4j
@AllArgsConstructor
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String userNameAttribute;
    private String email;
    private Role role;

    public static OAuth2UserInfo of(String provider, String userNameAttribute, Map<String, Object> attributes) {

        if (provider.equals("naver")) {
            log.info("클라이언트로부터 회원 정보를 가져옵니다.");
            OAuth2Attributes oAuth2Attributes = OAuth2Attributes.ofNaver(userNameAttribute, attributes);
            return new NaverUserInfo(oAuth2Attributes.getAttributes());
        }
        throw new NoSuchElementException("해당 로그인 방식은 지원하지 않습니다.");
    }

    private static OAuth2Attributes ofNaver(String userNameAttribute, Map<String, Object> attributes) {

        return OAuth2Attributes.builder()
                .attributes(attributes)
                .userNameAttribute(userNameAttribute)
                .email((String) attributes.get("email"))
                .role(Role.ROLE_USER)
                .build();
    }
}
