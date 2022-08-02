package com.exadel.finance.manager.wallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "w_id")
    private Long id;

    @Column(nullable = false, name = "w_name")
    private String name;

    @Column(nullable = false, name = "w_is_default")
    private boolean isDefault;
 
    @Column(nullable = false, name = "w_updated")
    private java.sql.Timestamp updated;
}
