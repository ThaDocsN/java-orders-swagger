package com.thadocizn.orders.models;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordNum;

    private double ordAmount;
    private double advanceAmount;
    private String ordDescription;


    @ManyToOne
    @JoinColumn(name = "custCode")
    private Customer custCode;

    @ManyToOne
    @JoinColumn(name = "agentCode")
    private Agent agentCode;


    public Order() {
    }

    public long getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(long ordNum) {
        this.ordNum = ordNum;
    }

    public double getOrdAmount() {
        return ordAmount;
    }

    public void setOrdAmount(double ordAmount) {
        this.ordAmount = ordAmount;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public String getOrdDescription() {
        return ordDescription;
    }

    public void setOrdDescription(String ordDescription) {
        this.ordDescription = ordDescription;
    }
}
