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
import lombok.RequiredArgsConstructor;
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

        return categoryMapper.toDto(categoryDao.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(
                        "No category #" + id + " or it has been deleted")));
    }

    @Override
    public CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        category.setId(categoryId);
        Profile profile = profileService.findByUserIdWithValidation();
        category.setProfile(profile);
        return categoryMapper.toDto(categoryDao.save(category));
    }

    @Override
    public void delete(Long id) {
        Category category = categoryDao.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(
                        "No category #" + id + " or it has been deleted"));

        if (transactionDao.findAllByCategory(category) != null) {
            throw new CategoryNotFoundException("This category is used. You can't delete it");
        }

        categoryDao.deleteById(id);

    }

    @Override
    public Long save(CategoryRequestDto categoryRequestDto) {
        Profile profile = profileService.findByUserIdWithValidation();
        Category category = categoryMapper.toEntity(categoryRequestDto);
        category.setProfile(profile);
        return categoryDao.save(category).getId();
    }

    @Override
    public List<CategoryResponseDto> findAllByCategoryType(TransactionTypeParameter categoryType) {
        Profile profile = profileService.findByUserIdWithValidation();
        List<Category> categories = categoryDao
                .findAllByCategoryTypeAndProfile(categoryType, profile);
        return categoryMapper.listToDtoList(categories);
    }

}
