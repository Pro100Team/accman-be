package com.manager.finance.category.model.entity;

import com.manager.finance.category.model.entity.api.CategoryType;
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

@Entity
@Table(name = "default_subcategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "def_sub_name", unique = true, nullable = false)
    private String name;
    @Column(name = "def_sub_color", nullable = false)
    private String color;
    @Column(name = "def_sub_type", nullable = false)
    private CategoryType categoryType;
    @ManyToOne
    @JoinColumn(name = "def_sub_parent_cat_id")
    private DefaultCategory parentCategoryId;
}
