package com.fazaJmartFH.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice
{
    public ArrayList<Record> history;
    public int productCount;
    public Shipment shipment;

    public static class Record
    {
        public Date date;
        public String message;
        public Status status;
    }
}
