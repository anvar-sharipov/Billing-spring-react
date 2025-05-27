package com.WEAK.telekom.models;
import com.WEAK.telekom.models.HozOrBudType;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;




@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"number", "etrap_id"})
)
@NoArgsConstructor
@AllArgsConstructor
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", length = 500)
    private String number;

    @ManyToOne
    @JoinColumn(name = "etrap_id")
    private Etrap etrap;

    @Column(name = "surname", length = 500)
    private String surname;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "street", length = 500)
    private String street;

    @Column(name = "home", length = 500)
    private String home;

    @Column(name = "flat", length = 500)
    private String flat;

    @Column(name = "account")
    private Integer account;

    @Column(name = "login")
    private String login;

    @Column(name = "dogowor")
    private String dogowor;

    @Column(name = "abonplata", precision = 5, scale = 2, nullable = true)
    private BigDecimal abonplata = BigDecimal.ZERO;

    @Column(name = "beneficiary")
    private boolean beneficiary = false;

    @Column(name = "is_deactivated")
    private boolean isDeactivated = false;

    @Column(name = "deactivated_at")
    private LocalDateTime deactivatedAt;

    // Автоматически устанавливаем дату деактивации перед обновлением
    @PreUpdate
    private void onPreUpdate() {
        if (isDeactivated && deactivatedAt == null) {
            deactivatedAt = LocalDateTime.now();
        } else if (!isDeactivated && deactivatedAt != null) {
            deactivatedAt = null;
        }
    }

    @Column(name = "is_enterprises")
    private boolean isEnterprises = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "hb")
    private HozOrBudType hb;

    @ManyToMany
    @JoinTable(
            name = "user_service",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<AbonentService> service;

    @Column(name = "image", length = 1000)
    private String image;  // Ссылка на изображение

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserBalanceSaldo balance;
}
