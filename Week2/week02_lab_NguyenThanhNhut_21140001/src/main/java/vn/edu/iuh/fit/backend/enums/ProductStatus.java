package vn.edu.iuh.fit.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ACTIVE(1), INACTIVE(0), DELETED(-1);
    private int value;

    private ProductStatus(int value) {
        this.value = value;
    }
}
