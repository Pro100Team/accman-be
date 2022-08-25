package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.api.CategoryType;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileCategoryDao extends JpaRepository<ProfileCategory, Long> {
    List<ProfileCategory> findByCategoryTypeAndProfileIdAndIsDeleted(CategoryType categoryType,
                                                             Profile profile,
                                                             Boolean isDeleted);

    Optional<ProfileCategory> findByIdAndIsDeleted(Long parentCategoryId, boolean b);

    ProfileCategory findByCategoryIdAndProfileId(Category parentCategory, Profile profile);
}
