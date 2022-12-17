package smwu.smwurestaurantinfo.entity.member;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String value;


    Role(String value) {
        this.value = value;
    }
}
