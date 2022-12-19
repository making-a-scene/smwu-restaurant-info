package smwu.smwurestaurantinfo.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("로그인을 요청한 회원의 정보를 provider로부터 불러옵니다.");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = getAttributesByProvider(provider, oAuth2User);

        return generateMemberIfNotExists(provider, userNameAttributeName, attributes);
    }

    private OAuth2User generateMemberIfNotExists(String provider, String userNameAttribute, Map<String, Object> userInfo) {
        log.info("회원 정보가 존재하지 않으면 회원을 생성합니다.");
        String email = (String) userInfo.get("email");
        if (memberService.findByEmail(email).isEmpty()) {
            memberService.generateNewMember(new GenerateMemberRequestDto(email));
        }
        userInfo.put("role", memberService.findByEmail(email).get().getRole());
        return OAuth2Attributes.of(provider, userNameAttribute, userInfo);
    }

    private Map<String, Object> getAttributesByProvider(String provider, OAuth2User oAuth2User) {
        if (provider.equals("naver")) {
            return (Map<String, Object>) oAuth2User.getAttributes().get("response");
        }
        throw new IllegalArgumentException("해당 로그인 방식은 지원하지 않습니다.");
    }
}
