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
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        return generateMemberIfNotExists(provider, userNameAttributeName, attributes);
    }

    private OAuth2User generateMemberIfNotExists(String provider, String userNameAttribute, Map<String, Object> attributes) {
        String email = (String) attributes.get("email");
        if (memberService.findByEmail(email).isEmpty()) {
            memberService.generateNewMember(new GenerateMemberRequestDto(email));
        }
        return OAuth2Attributes.of(provider, userNameAttribute, attributes);
    }
}
