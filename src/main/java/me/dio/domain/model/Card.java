package me.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "tb_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    @Column(name = "available_limit", precision = 13, scale = 2)
    private BigDecimal limit;

    @Column
    private String expiration;

    @Column
    private boolean cardActive;

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public boolean isCardActive() {
        LocalDate currentDate = LocalDate.now();
        String[] expirationParts = expiration.split("/");
        if (expirationParts.length == 2) {
            int month = Integer.parseInt(expirationParts[0]);
            int year = Integer.parseInt(expirationParts[1]);
            LocalDate cardExpiration = LocalDate.of(year, month, 1);
            cardActive = cardExpiration.isAfter(currentDate);
        } else {
            cardActive = false; // Tratamento para data de validade inválida
        }
        return cardActive;
    }

}
