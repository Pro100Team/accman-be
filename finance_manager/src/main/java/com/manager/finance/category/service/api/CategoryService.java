/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 23:00
 **/

package com.manager.finance.category.service.api;

import com.manager.finance.category.model.entity.Category;
import java.util.List;

public interface CategoryService {
    Category getById(Long id);

    Category getByNameOrCreate(String name);

    Category save(String name);

    List<Category> getByIsDefault();
}
