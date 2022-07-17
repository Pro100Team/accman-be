/**
 * @Autor GapSerg
 * @Create 2022-07-17.07.2022 21:19
 **/

package com.exadel.finance.manager.model.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    int id;
    @Getter
    @Setter
    String name;


}
