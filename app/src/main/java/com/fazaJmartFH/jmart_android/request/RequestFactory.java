package com.fazaJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * RequestFactory
 *
 * Digunakan untuk parent pada class request lain.
 */
public class RequestFactory
{
    private static final String URL_FORMAT_ID = "http://10.0.2.2:8080/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:8080/%s/page?page=%s&pageSize=%s";

    /**
     * getById Method
     *
     * Digunakan untuk mendapatkan akun pengguna melalui nomor identifikasi pengguna.
     *
     * @param parentURI URI.
     * @param id Nomor identifikasi pengguna.
     *
     * @return Akun pengguna.
     */
    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    /**
     * getPage Method
     *
     * Digunakan untuk menampilkan halaman.
     *
     * @param parentURI URI.
     * @param page Halaman berapa yang ingin ditampilkan.
     * @param pageSize Ukuran halaman/berapa banyak objek yang ingin ditampilkan.
     *
     * @return Halaman dengan produk.
     */
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI,page,pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}
