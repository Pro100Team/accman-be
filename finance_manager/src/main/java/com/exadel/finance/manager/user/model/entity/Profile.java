package com.exadel.finance.manager.user.model.entity;

import com.exadel.finance.manager.user.model.entity.api.Country;
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
@Table(name = "profile")
@Data
@RequiredArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    private Long id;

    @Column(name = "p_first_name")
    private String firstName;

    @Column(name = "p_last_name")
    private String lastName;

    @Column(name = "p_country")
    private Country country;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "p_user_id", referencedColumnName = "u_id")
    private User userId;

    @Column(name = "p_dt_update")
    private LocalDateTime dtUpdate;

}
