/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 22:15
 **/

package com.manager.finance.category.model.entity;

import com.sandbox.model.TransactionTypeParameter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", updatable = false)
    private Long id;

    @Column(name = "cat_name", nullable = false)
    private String name;

    @Column(name = "cat_color", nullable = false)
    private String color;

    @Column(name = "cat_type", nullable = false)
    private TransactionTypeParameter categoryType;

}
