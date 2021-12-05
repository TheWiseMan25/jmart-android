package com.fazaJmartFH.jmart_android.model;

import androidx.annotation.NonNull;

public class Product extends Serializable
{
    public int accountId;
    public String name;
    public int weight;
    public double discount;
    public double price;
    public boolean conditionUsed;
    public ProductCategory category;
    public byte shipmentPlans;

    @NonNull
    public String toString()
    {
        return name;
    }
}
