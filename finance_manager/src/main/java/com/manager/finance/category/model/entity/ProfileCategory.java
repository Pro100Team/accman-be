package com.manager.finance.category.model.entity;

import com.manager.finance.category.model.entity.api.CategoryType;
import com.manager.finance.user.model.entity.Profile;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "profile_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_cat_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pr_cat_profile_id", nullable = false)
    private Profile profileId;

    @ManyToOne
    @JoinColumn(name = "pr_cat_category_id", nullable = false)
    private Category categoryId;

    @Column(name = "pr_cat_color", nullable = false)
    private String color;

    @Column(name = "pr_cat_type", nullable = false)
    private CategoryType categoryType;

    @Column(name = "pr_cat_is_deleted", nullable = false)
    private Boolean isDeleted;
}
