package smwu.smwurestaurantinfo.api.dto;

import lombok.Data;
import smwu.smwurestaurantinfo.domain.member.Member;
import smwu.smwurestaurantinfo.domain.member.Role;
import smwu.smwurestaurantinfo.domain.member.UserStatus;

@Data
public class GenerateMemberRequestDto {
    private final String email;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .role(Role.ROLE_USER)
                .status(UserStatus.ROLE_UNAUTHENTICATED)
                .build();
    }
}
