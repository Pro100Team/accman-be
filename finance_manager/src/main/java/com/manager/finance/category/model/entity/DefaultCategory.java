package com.manager.finance.category.model.entity;

import com.manager.finance.category.model.entity.api.CategoryType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "default_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "def_cat_name", unique = true, nullable = false)
    private String name;
    @Column(name = "def_cat_color", nullable = false)
    private String color;
    @Column(name = "def_cat_type", nullable = false)
    private CategoryType categoryType;
}
