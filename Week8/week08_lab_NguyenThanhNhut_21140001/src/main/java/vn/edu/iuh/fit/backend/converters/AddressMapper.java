/*
 * @ (#) AddressMapper.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.converters;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import com.neovisionaries.i18n.CountryCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import vn.edu.iuh.fit.backend.dtos.AddressDTO;
import vn.edu.iuh.fit.backend.models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "country", target = "country", qualifiedByName = "countryToString")
    AddressDTO toDTO(Address address);

    @Mapping(source = "country", target = "country", qualifiedByName = "stringToCountry")
    Address toEntity(AddressDTO addressDTO);

    @Named("countryToString")
    default String countryToString(CountryCode countryCode) {
        if (countryCode == null) {
            return null;
        }
        return countryCode.name(); // Chuyển Enum thành String (tên của Enum)
    }

    @Named("stringToCountry")
    default CountryCode stringToCountry(String countryCode) {
        if (countryCode == null || countryCode.isEmpty()) {
            return null;
        }
        if ("Vietnam".equalsIgnoreCase(countryCode)) {
            return CountryCode.VN;
        }
        return CountryCode.valueOf(countryCode); // Chuyển String thành Enum
    }
}
