package com.fazaJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class PaymentRequest extends StringRequest
{
    private static final String URL = "http://10.0.2.2:8080/payment/create";
    private final Map<String , String> params;

    public PaymentRequest(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId", Integer.toString(buyerId));
        params.put("productId", Integer.toString(productId));
        params.put("productCount", Integer.toString(productCount));
        params.put("shipmentAddress", shipmentAddress);
        params.put("shipmentPlan", Byte.toString(shipmentPlan));
    }

    public Map<String , String> getParams() {
        return params;
    }
}