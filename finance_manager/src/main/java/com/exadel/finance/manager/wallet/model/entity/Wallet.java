package com.exadel.finance.manager.wallet.model.entity;

import com.exadel.finance.manager.wallet.model.entity.api.DefaultCurrency;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_id", updatable = false)
    private Long id;

    @Column(name = "w_name", nullable = false)
    private String name;
    @Column(name = "w_currency", nullable = false)
    private String currency;

    @Column(name = "w_is_default")
    private Boolean isDefault;

    @Column(name = "w_is_deleted")
    private Boolean isDeleted;

    @Column(name = "w_used_at")
    private LocalDateTime usedAt;

    @ManyToOne
    @JoinColumn(name = "w_user_id", referencedColumnName = "u_id")
    private User userId;

    public void setCurrency(DefaultCurrency currency) {
        this.currency = currency.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null
                || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Wallet wallet = (Wallet) o;
        return id != null && Objects.equals(id, wallet.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
