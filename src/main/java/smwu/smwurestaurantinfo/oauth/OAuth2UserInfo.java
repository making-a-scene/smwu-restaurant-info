package smwu.smwurestaurantinfo.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public interface OAuth2UserInfo extends OAuth2User {
    Map<String, Object> getAttributes();
    String getEmail();
}