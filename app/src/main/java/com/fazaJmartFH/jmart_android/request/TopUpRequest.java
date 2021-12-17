package com.fazaJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import com.fazaJmartFH.jmart_android.LoginActivity;

/**
 * TopUpRequest Class
 *
 * Digunakan ketika ada request top up.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class TopUpRequest extends StringRequest {
    private static final String URL =  "http://10.0.2.2:8080/account/" + LoginActivity.getLoggedAccount().id + "/topUp";
    private final Map<String , String> params;

    /**
     * TopUpRequest Constructor
     *
     * Digunakan untuk memberi nilai pada params.
     *
     * @param id Nomor identifikasi pengguna.
     * @param balance Saldo pengguna.
     */
    public TopUpRequest
            (
                    int id,
                    double balance,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("id", Integer.toString(id));
        params.put("balance", Double.toString(balance));
    }

    /**
     * getParams Method
     *
     * Digunakan untuk mendapatkan params.
     *
     * @return params.
     */
    public Map<String , String> getParams() {
        return params;
    }
}
