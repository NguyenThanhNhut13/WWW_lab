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
    @Mapping(source = "zipcode", target = "zipCode")
    AddressDTO toDTO(Address address);

    @Mapping(source = "country", target = "country", qualifiedByName = "stringToCountry")
    @Mapping(source = "zipCode", target = "zipcode")
    Address toEntity(AddressDTO addressDTO);

    // Conversion from CountryCode enum to String
    @Named("countryToString")
    default String countryToString(CountryCode country) {
        return country != null ? country.name() : null;
    }

    // Conversion from String to CountryCode enum
    @Named("stringToCountry")
    default CountryCode stringToCountry(String country) {
        try {
            return country != null ? CountryCode.valueOf(country) : null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
