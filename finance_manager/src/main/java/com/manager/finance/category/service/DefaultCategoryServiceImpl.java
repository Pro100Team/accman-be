package com.manager.finance.category.service;

import com.manager.finance.category.dao.DefaultCategoryDao;
import com.manager.finance.category.model.entity.DefaultCategory;
import com.manager.finance.category.service.api.DefaultCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCategoryServiceImpl implements DefaultCategoryService {
    private final DefaultCategoryDao defaultCategoryDao;

    @Override
    public List<DefaultCategory> getAll() {
        return defaultCategoryDao.findAll();
    }
}
