package com.challenge.picpay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "document", unique = true)
    private String document;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    @Builder.Default
    private BigDecimal balance = ZERO;

    @JoinColumn(name = "wallet_type_id")
    @ManyToOne
    private WalletType walletType;

    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }
}
