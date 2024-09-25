package vn.edu.iuh.fit.enums;

public enum EmployeeStatus {
    WORKING(1), ON_LEAVE(0), RESIGNED(-1);
    private final int value;

    EmployeeStatus(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }

    /**
     * Convert int value to enum
     * @param value
     * @return
     */
    public static EmployeeStatus convertEnum(int value) {
        for (EmployeeStatus status : EmployeeStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
