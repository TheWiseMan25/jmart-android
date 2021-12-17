package com.fazaJmartFH.jmart_android.request;

import androidx.annotation.Nullable;
import com.fazaJmartFH.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * CreateProductRequest Class
 *
 * Digunakan untuk mengatur request ketika ingin membuat produk baru.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class CreateProductRequest extends StringRequest
{

    public static final String URL = "http://10.0.2.2:8080/product/create";
    public final Map<String,String> params;

    /**
     * CreateProductRequest Constructor
     *
     * Digunakan untuk memberi nilai pada params.
     *
     * @param name Nama produk.
     * @param weight Massa produk.
     * @param conditionUsed Kondisi produk.
     * @param price Harga produk.
     * @param discount Diskon pada produk.
     * @param category Kategori produk.
     * @param shipmentPlans Jenis pengiriman produk.
     */
    public CreateProductRequest(String name, String weight, String conditionUsed, String price, String discount, String category, String shipmentPlans, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        Integer i = LoginActivity.loggedAccount.id;
        params.put("accountId", i.toString());
        params.put("name",name);
        params.put("weight",weight);
        params.put("conditionUsed",conditionUsed);
        params.put("price", price);
        params.put("discount", discount);
        params.put("category", category);
        params.put("shipmentPlans", shipmentPlans);
    }

    /**
     * getParams Method
     *
     * Digunakan untuk mendapatkan params.
     *
     * @return params.
     */
    public Map<String,String> getParams(){return params;}
}