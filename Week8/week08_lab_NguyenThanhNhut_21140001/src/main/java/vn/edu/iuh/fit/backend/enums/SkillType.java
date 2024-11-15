package vn.edu.iuh.fit.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SkillType {
    SOFT_SKILL("SOFT_SKILL"), UNSPECIFIED("UNSPECIFIED"), TECHNICAL_SKILL("TECHNICAL_SKILL");

    private final String value;

    SkillType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SkillType fromValue(String value) {
        for (SkillType type : SkillType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown SkillType with value: " + value);
    }

}
