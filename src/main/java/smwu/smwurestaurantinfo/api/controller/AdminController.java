package smwu.smwurestaurantinfo.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smwu.smwurestaurantinfo.domain.member.Member;
import smwu.smwurestaurantinfo.service.MemberService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private static ResponseEntity<Object> buildResponseEntity(Object o) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(o, httpHeaders, OK);
    }

    @GetMapping("/admin/setting/new")
    public ResponseEntity<Object> addNewAdmin(@RequestParam(name = "id") Long id) {
        memberService.giveAuthToAdmin(id);
        return buildResponseEntity(memberService.findById(id));
    }

    @GetMapping("/admin/setting/auth")
    public ResponseEntity<Object> giveAuthToSmwuStudents(HttpServletRequest request, @RequestParam(name = "id") Long... ids) {
        List<Member> members = new ArrayList<>();
        for (Long id : ids) {
            memberService.giveAuthToAuthenticatedMember(id);
            members.add(memberService.findById(id));
        }
        return buildResponseEntity(members);
    }
}
