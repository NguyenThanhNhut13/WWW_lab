package vn.edu.iuh.fit.backend.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SkillLevel {

    BEGINNER("BEGINNER"), INTERMEDIATE("INTERMEDIATE"), ADVANCED("ADVANCED"), PROFESSIONAL("PROFESSIONAL"), MASTER("MASTER");

    private final String value;

    SkillLevel(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SkillLevel fromValue(String value) {
        for (SkillLevel level : SkillLevel.values()) {
            if (level.value.equals(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown SkillLevel with value: " + value);
    }


}
