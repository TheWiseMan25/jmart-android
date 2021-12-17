package com.fazaJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

import com.fazaJmartFH.jmart_android.LoginActivity;

/**
 * RegisterStoreRequest Class
 *
 * Digunakan ketika ada request register store.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class RegisterStoreRequest extends StringRequest
{
    private static final String URL =  "http://10.0.2.2:8080/account/" + LoginActivity.getLoggedAccount().id + "/registerStore";
    private final Map<String , String> params;

    /**
     * RegisterStoreRequest Constructor
     *
     * Digunakan untuk memberi nilai pada params.
     *
     * @param id Nomor identifikasi pengguna.
     * @param name Nama toko
     * @param address Alamat toko.
     * @param phoneNumber Nomor telepon toko.
     */
    public RegisterStoreRequest(int id, String name, String address, String phoneNumber, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        Integer  i = id;
        params = new HashMap<>();
        params.put("id", i.toString());
        params.put("name", name);
        params.put("address", address);
        params.put("phoneNumber", phoneNumber);
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