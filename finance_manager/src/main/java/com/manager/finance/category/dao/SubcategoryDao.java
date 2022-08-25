package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryDao extends JpaRepository<ProfileSubcategory, Long> {
    List<ProfileSubcategory> findByParentCategoryIdAndProfileIdAndIsDeleted(Long category,
                                                                            Profile profile,
                                                                            Boolean isDeleted);
}
