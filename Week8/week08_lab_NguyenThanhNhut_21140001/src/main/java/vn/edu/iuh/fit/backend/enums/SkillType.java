package vn.edu.iuh.fit.backend.enums;

public enum SkillType {
    SOFT_SKILL(1), UNSPECIFIED(2), TECHNICAL_SKILL(3);

    private final int value;

    SkillType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
