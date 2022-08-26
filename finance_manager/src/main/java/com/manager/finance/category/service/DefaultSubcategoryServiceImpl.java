package com.manager.finance.category.service;

import com.manager.finance.category.dao.DefaultSubcategoryDao;
import com.manager.finance.category.model.entity.DefaultSubcategory;
import com.manager.finance.category.service.api.DefaultSubcategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultSubcategoryServiceImpl implements DefaultSubcategoryService {
    private final DefaultSubcategoryDao defaultSubcategoryDao;

    @Override
    public List<DefaultSubcategory> getAll() {
        return defaultSubcategoryDao.findAll();
    }
}
