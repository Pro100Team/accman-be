package com.exadel.finance.manager.service;

import com.exadel.finance.manager.config.rsql.CustomRsqlVisitor;
import com.exadel.finance.manager.model.dto.request.RequestDto;
import com.exadel.finance.manager.model.dto.response.ResponseDto;
import com.exadel.finance.manager.repository.SpecificationRepository;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
@Log4j2
public class AbstractEntityService<T, Request extends RequestDto, Response extends ResponseDto>
        extends MapperServiceImpl<Request, Response>
        implements GenericService<T, Request, Response> {

    private final SpecificationRepository<T, Long> repository;
    private final MapperService<Request, Response> mapper;

    /*
    @Override
    public T saveOrUpdate(T entity) {
        log.debug("saveOrUpdate() is called for {}", entity);
        return repository.save(entity);
    }


    @Override
    public Response saveOrUpdate(Request request, T entity) {
        return null;
    }*/


    @Override
    public Response saveOrUpdate(Request request, T entity) {
        mapper.map(request, entity);
        return null;
    }

    @Override
    public T findById(Long id) {
        log.debug("findById() is called for {}", id);
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(id, "Unable to locate object in database: "));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleteById() is called for {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll(String searchPredicate) {
        log.debug("findAll(Specification<T> specification) is called");
        return searchPredicate == null
                ? repository.findAll()
                : repository.findAll(parsePredicate(searchPredicate));
    }

    private Specification<T> parsePredicate(String search) {
        Node rootNode = new RSQLParser().parse(search);
        return rootNode.accept(new CustomRsqlVisitor<T>());
    }
}
