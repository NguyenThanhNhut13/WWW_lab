/*
 * @ (#) EmployeeStatus.java       1.0     26/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.enums;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 26/09/2024
 * @version:    1.0
 */

import lombok.Getter;

@Getter
public enum EmployeeStatus {
    ACTIVE(1), INACTIVE(0), TERMINATED(-1);
    private int value;

    private EmployeeStatus(int value) {
        this.value = value;
    }

}
