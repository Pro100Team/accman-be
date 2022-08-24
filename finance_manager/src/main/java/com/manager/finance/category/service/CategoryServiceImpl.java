/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 23:05
 **/

package com.manager.finance.category.service;

import com.manager.finance.category.dao.CategoryDao;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.exception.category.CategoryNotFoundException;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.manager.finance.transaction.dao.TransactionDao;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.service.api.ProfileService;
import com.sandbox.model.CategoryRequestDto;
import com.sandbox.model.CategoryResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    private final CategoryMapper categoryMapper;

    private final ProfileService profileService;

    private final TransactionDao transactionDao;

    @Override
    public CategoryResponseDto getById(Long id) {

        return categoryMapper.categoryToResponseDto(categoryDao.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(
                        "No category #" + id + " or it has been deleted")));
    }

    @Override
    public CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.requestDtoToCategory(categoryRequestDto);
        category.setId(categoryId);
        Profile profile = profileService.findByUserIdWithValidation();
        category.setProfile(profile);
        return categoryMapper.categoryToResponseDto(categoryDao.save(category));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        Profile profile = profileService.findByUserIdWithValidation();
        Optional<Category> category = categoryDao.findByIdAndProfile(id, profile);

        if (category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (transactionDao.findAllByCategory(category.get()).size() != 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        categoryDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public Long save(CategoryRequestDto categoryRequestDto) {
        Profile profile = profileService.findByUserIdWithValidation();
        Category category = categoryMapper.requestDtoToCategory(categoryRequestDto);
        category.setProfile(profile);
        return categoryDao.save(category).getId();
    }

    @Override
    public List<CategoryResponseDto> findAllByCategoryType(TransactionTypeParameter categoryType) {
        Profile profile = profileService.findByUserIdWithValidation();
        List<Category> categories = categoryDao
                .findAllByCategoryTypeAndProfile(categoryType, profile);
        return categoryMapper.categoryListToResponseDtoList(categories);
    }

}
