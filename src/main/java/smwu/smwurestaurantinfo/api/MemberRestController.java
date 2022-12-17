package smwu.smwurestaurantinfo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smwu.smwurestaurantinfo.api.dto.GenerateMemberRequestDto;
import smwu.smwurestaurantinfo.entity.member.Member;
import smwu.smwurestaurantinfo.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @RequestMapping(value = "/join")
    public Map<String, Object> joinNewMember(@RequestBody GenerateMemberRequestDto dto) {
        Long memberId = memberService.generateNewMember(dto);
        Member generated = memberService.findById(memberId);
        Map<String, Object> response = new HashMap<>();
        response.put("email", generated.getEmail());
        response.put("role", generated.getRole());

    }

}
