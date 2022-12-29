package smwu.smwurestaurantinfo.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import smwu.smwurestaurantinfo.service.MemberService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;

}
