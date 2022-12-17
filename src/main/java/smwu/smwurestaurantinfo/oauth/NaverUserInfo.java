package smwu.smwurestaurantinfo.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import smwu.smwurestaurantinfo.entity.member.Role;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = ((Role) attributes.get("role")).getValue();
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getName() {
        return (String) attributes.get("nickname");
    }
}
