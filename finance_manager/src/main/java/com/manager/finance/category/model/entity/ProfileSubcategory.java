package com.manager.finance.category.model.entity;

import com.manager.finance.user.model.entity.Profile;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile_sub_cat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_sub_cat_id")
    private Long id;

    @Column(name = "pr_sub_cat_category_id", nullable = false)
    private Long parentCategoryId;

    @ManyToOne
    @JoinColumn(name = "pr_sub_cat_subcategory_id", nullable = false)
    private Category subcategoryId;

    @OneToOne
    @JoinColumn(name = "pr_sub_cat_profile_id", nullable = false)
    private Profile profileId;

    @Column(name = "pr_sub_cat_color", nullable = false)
    private String color;

    @Column(name = "pr_sub_cat_is_deleted", nullable = false)
    private Boolean isDeleted;
}
