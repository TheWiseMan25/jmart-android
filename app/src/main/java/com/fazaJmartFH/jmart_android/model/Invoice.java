package com.fazaJmartFH.jmart_android.model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Invoice extends Serializable
{
    public int buyerId;
    public int complaintId;
    public  Date date;
    public int productId;
    public Rating rating;
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static enum Status
    {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED
    }

    public static enum Rating
    {
        NONE, BAD, NEUTRAL, GOOD
    }

}
