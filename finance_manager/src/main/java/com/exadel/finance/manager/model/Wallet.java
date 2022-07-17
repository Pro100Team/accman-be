package com.exadel.finance.manager.model;

import com.exadel.finance.manager.config.currency.list.Currency;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE wallets SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal amount;

    private Boolean isDefault;

    @ColumnDefault("false")
    private boolean isDeleted;

    public Wallet(User user, String name, Currency currency, BigDecimal amount, boolean isDefault) {
        this.user = user;
        this.name = name;
        this.currency = currency;
        this.amount = amount;
        this.isDefault = isDefault;
    }
}
