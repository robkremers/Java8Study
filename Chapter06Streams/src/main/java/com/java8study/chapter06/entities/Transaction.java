package com.java8study.chapter06.entities;

import java.io.Serializable;

import com.java8study.chapter06.enums.*;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1258674737474032187L;
	private final Currency currency;
    private final double value;

    public Transaction(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return currency + " " + value;
    }
}	
