/*
 * @ (#) AddressDTO.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDTO implements Serializable {
    private Long id;
    private String street;
    private String city;
    private String number;
    private String country;
    private String zipCode;

    public String getFullAddress() {
        String fullAddress = "";
        if (this.number != null) {
            fullAddress += this.number + ", ";
        }
        if (this.street != null) {
            fullAddress += this.street + ", ";
        }
        if (this.city != null) {
            fullAddress += this.city + ", ";
        }
        if (this.country != null) {
            fullAddress += this.country + ", ";
        }
        if (this.zipCode != null) {
            fullAddress += this.zipCode;
        }
        return fullAddress;
    }
}
