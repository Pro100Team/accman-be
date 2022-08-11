/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:02
 **/

package com.manager.finance.transaction.model.entity;

import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.model.entity.api.DefaultCurrency;
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
@Table(name = "transactions")
@Data
@RequiredArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id", updatable = false)
    private Long id;

    @Column(name = "tr_name", nullable = false)
    private String name;

    @Column(name = "tr_currency", nullable = false)
    private DefaultCurrency currency;

    @Column(name = "tr_is_deleted")
    private Boolean isDeleted;

    @Column(name = "tr_last_updated")
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "tr_wallet_id", referencedColumnName = "w_id", nullable = false)
    private Wallet wallet;

}
