package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.dto.request.RequestDto;
import com.exadel.finance.manager.model.dto.response.ResponseDto;
import java.util.List;

public interface GenericService<T, Request extends RequestDto, Response extends ResponseDto> {
    Response saveOrUpdate(Request request, T entity);

    T findById(Long id);

    void deleteById(Long id);

    List<T> findAll(String searchSpecification);
}
