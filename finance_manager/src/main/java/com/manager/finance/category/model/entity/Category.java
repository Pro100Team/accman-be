/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 22:15
 **/

package com.manager.finance.category.model.entity;

import com.manager.finance.user.model.entity.Profile;
import com.sandbox.model.TransactionTypeParameter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "cat_profile_id", referencedColumnName = "p_id", nullable = false)
    private Profile profile;

    public Category(String name, String color, TransactionTypeParameter categoryType,
                    Profile profile) {
        this.name = name;
        this.color = color;
        this.categoryType = categoryType;
        this.profile = profile;
    }
}
