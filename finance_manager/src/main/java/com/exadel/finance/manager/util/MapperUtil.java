package com.exadel.finance.manager.util;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class MapperUtil {
    private static ModelMapper mapper;

    @Autowired
    public MapperUtil(ModelMapper mapper) {
        MapperUtil.mapper = mapper;
    }

    public static <FromT, T> List<T> mapToList(List<FromT> fromList,
                                               final Class<T> toClass) {
        return fromList
                .stream()
                .map(from -> mapper.map(from, toClass))
                .collect(Collectors.toList());
    }

    public static <FromT, T> T map(FromT fromObject, final Class<T> toClass) {
        return mapper.map(fromObject, toClass);
    }
}
