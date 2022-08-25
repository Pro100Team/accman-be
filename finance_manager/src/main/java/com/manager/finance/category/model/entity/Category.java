/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 22:15
 **/

package com.manager.finance.category.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", updatable = false)
    private Long id;

    @Column(name = "cat_name", nullable = false, unique = true)
    private String name;

    @Column(name = "cat_is_default",nullable = false)
    private Boolean isDefault;

}
