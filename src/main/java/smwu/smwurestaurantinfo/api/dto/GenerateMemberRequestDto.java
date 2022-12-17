package smwu.smwurestaurantinfo.api.dto;

import lombok.Data;
import smwu.smwurestaurantinfo.entity.member.Member;

@Data
public class GenerateMemberRequestDto {
    private final String email;

    public Member toEntity() {
        return Member.builder().email(email).build();
    }
}
