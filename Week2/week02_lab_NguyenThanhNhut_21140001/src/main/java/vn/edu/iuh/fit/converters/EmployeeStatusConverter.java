/*
 * @ (#) EmployeeStatusConverter.java       1.0     26/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.converters;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 26/09/2024
 * @version:    1.0
 */

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.enums.EmployeeStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        if (employeeStatus == null) {
            return null;
        }
        return employeeStatus.getValue();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return Stream.of(EmployeeStatus.values())
                .filter(c -> c.getValue() == integer)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
