package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.dto.request.RequestDto;
import com.exadel.finance.manager.model.dto.response.ResponseDto;

public interface MapperService<Request extends RequestDto, Response extends ResponseDto> {
    Response map(Request request, final Class<Response> responseClass);
}
