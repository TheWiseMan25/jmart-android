package com.fazaJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginRequest Class
 *
 * Digunakan untuk mengatur request log in.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class LoginRequest extends StringRequest
{
    private static final String URL = "http://10.0.2.2:8080/account/login";
    private final Map<String, String> params;

    /**
     * LoginRequest Constructor
     *
     * Digunakan untuk memberi nilai pada params.
     *
     * @param email Alamat surel pengguna.
     * @param password Kata sandi pengguna.
     */
    public LoginRequest(String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    /**
     * getParams Method
     *
     * Digunakan untuk mendapatkan params.
     *
     * @return params.
     */
    public Map<String, String> getParams() {return params;}
}