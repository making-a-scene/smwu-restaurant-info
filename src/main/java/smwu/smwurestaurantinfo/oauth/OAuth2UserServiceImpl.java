package smwu.smwurestaurantinfo.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import smwu.smwurestaurantinfo.api.dto.GenerateMemberRequestDto;
import smwu.smwurestaurantinfo.service.MemberService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return generateMemberIfNotExists(oAuth2User.getAttributes());
    }

    private OAuth2User generateMemberIfNotExists(Map<String, Object> userAttributes) {
        String email = (String) userAttributes.get("email");
        if (memberService.findByEmail(email).isEmpty()) {
            memberService.generateNewMember(new GenerateMemberRequestDto(email));
        }
        return new NaverUserInfo(userAttributes);
    }
}
