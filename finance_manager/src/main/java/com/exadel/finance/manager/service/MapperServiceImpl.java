package com.exadel.finance.manager.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperServiceImpl<Request, Response> implements MapperService<Request, Response> {
    @Autowired
    private ModelMapper mapper;

    @Override
    public Response map(Request request, Class<Response> responseClass) {
        return mapper.map(request, responseClass);
    }
}
