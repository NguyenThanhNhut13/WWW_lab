/*
 * @ (#) ObjectMapperContextResolver.java       1.0     26/09/2024
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperContextResolver() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return objectMapper;
    }
}
