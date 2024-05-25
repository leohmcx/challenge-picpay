package com.challenge.picpay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_wallet_type")
public class WalletType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String description;

    @Getter
    @AllArgsConstructor
    public enum Enum {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        private final Long id;
        private final String description;

        public WalletType get() {
            return WalletType.builder().id(id).description(description).build();
        }

        public static boolean isMerchant(String description) {
            return Arrays.stream(Enum.values())
                    .anyMatch(e -> e.getDescription().equalsIgnoreCase(description));
        }
    }
}
