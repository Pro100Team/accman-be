package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.api.Type;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileCategoryDao extends JpaRepository<ProfileCategory, Long> {
    List<ProfileCategory> findByTypeAndProfileIdAndIsDeleted(Type categoryType, Profile profile,
                                                             Boolean isDeleted);
}
