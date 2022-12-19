package smwu.smwurestaurantinfo.domain.member;

import lombok.Getter;

@Getter
public enum UserStatus {

    ROLE_UNAUTHENTICATED("ROLE_UNAUTHENTICATED"),
    ROLE_AUTHENTICATED("ROLE_AUTHENTICATED");

    private final String value;
    UserStatus(String value) {
        this.value = value;
    }
}
