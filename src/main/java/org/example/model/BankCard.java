package org.example.model;

import java.math.BigDecimal;

public class BankCard
{
    private long id;
    private String cardNumber;
    private int CVV;
    private BigDecimal balance;

    public BankCard() {}

    public BankCard(long id, String cardNumber, int CVV, BigDecimal balance)
    {
        this.id = id;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.balance = balance;
    }

    public BankCard(String cardNumber, int CVV, BigDecimal balance)
    {
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.balance = balance;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber;}

    public int getCVV() { return CVV; }
    public void setCVV(int CVV) { this.CVV = CVV;}

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance;}

    @Override
    public String toString() {
        return "BankCard [id=" + id + ", cardNumber=" + cardNumber + ", CVV= " + CVV + ", balance=" + balance + "]";
    }
}
