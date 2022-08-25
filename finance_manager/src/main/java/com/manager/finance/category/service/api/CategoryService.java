/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 23:00
 **/

package com.manager.finance.category.service.api;

import com.manager.finance.category.model.entity.Category;

public interface CategoryService {
    Category getById(Long id);

    Category getByName(String name);

    Category save(String name);
}
