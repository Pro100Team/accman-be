package com.exadel.managger.finance.wallet.model.entity;

import com.exadel.managger.finance.user.model.entity.Profile;
import com.exadel.managger.finance.wallet.model.entity.api.DefaultCurrency;
import java.time.LocalDateTime;
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
@Table(name = "wallets")
@Data
@RequiredArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_id", updatable = false)
    private Long id;

    @Column(name = "w_name", nullable = false)
    private String name;

    @Column(name = "w_currency", nullable = false)
    private DefaultCurrency currency;

    @Column(name = "w_is_default")
    private Boolean isDefault;

    @Column(name = "w_is_deleted")
    private Boolean isDeleted;

    @Column(name = "w_used_at")
    private LocalDateTime usedAt;

    @ManyToOne
    @JoinColumn(name = "w_profile_id", referencedColumnName = "p_id", nullable = false)
    private Profile profileId;

}
