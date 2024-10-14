/*
 * @ (#) AppUtils.java       1.0     15/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.util;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 15/10/2024
 * @version:    1.0
 */

public class AppUtils {
    public static String[] splitFullName(String fullName) {
        String[] nameParts = fullName.trim().split("\\s+");

        String firstName = "";
        String middleName = "";
        String lastName = "";

        // Check number of parts
        if (nameParts.length > 0) {
            firstName = nameParts[0];
        }
        if (nameParts.length > 2) {
            lastName = nameParts[nameParts.length - 1];
            middleName = String.join(" ", java.util.Arrays.copyOfRange(nameParts, 1, nameParts.length - 1));
        } else if (nameParts.length == 2) {
            lastName = nameParts[1];
        }

        return new String[]{firstName, middleName, lastName};
    }
}
