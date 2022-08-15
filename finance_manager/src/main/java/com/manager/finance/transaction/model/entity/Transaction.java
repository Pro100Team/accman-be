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
import org.example.model.TransactionTypeParameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "transactions")
@Data
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE transactions SET tr_is_deleted = true WHERE tr_id=?")
@Where(clause = "tr_is_deleted=false")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id", updatable = false)
    private Long id;

    @Column(name = "tr_type", nullable = false)
    private TransactionTypeParameter typeOf;

    @Column(name = "tr_amount", nullable = false)
    private int amount;

    @Column(name = "tr_category", nullable = false)
    private String category;

    @Column(name = "tr_sub_category", nullable = false)
    private String subcategory;

    @Column(name = "tr_payer", nullable = false)
    private String payer;

    @Column(name = "tr_notes", nullable = false)
    private String notes;

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
