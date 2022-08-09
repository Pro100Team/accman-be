package com.exadel.managger.finance.user.dao;

import com.exadel.managger.finance.user.model.entity.Profile;
import com.exadel.managger.finance.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDao extends JpaRepository<Profile, Long> {
    Profile findProfileByUserIdAndIsDeleted(User user, Boolean isDeleted);

    Page<Profile> findProfileByUserId(User userId, Pageable pageable);
}
