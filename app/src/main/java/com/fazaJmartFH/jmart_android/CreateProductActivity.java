package com.fazaJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.fazaJmartFH.jmart_android.model.Product;
import com.fazaJmartFH.jmart_android.request.CreateProductRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CreateProductActivity Class
 *
 * Digunakan untuk menampilkan pengaturan untuk membuat produk baru.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class CreateProductActivity extends AppCompatActivity
{

    private static final Gson gson = new Gson();
    private static  Product product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        TextInputLayout nameLayout = (TextInputLayout) findViewById(R.id.layout_name_createproduct);
        TextInputLayout weightLayout = (TextInputLayout) findViewById(R.id.layout_weight_createproduct);
        TextInputLayout priceLayout = (TextInputLayout) findViewById(R.id.layout_price_createproduct);
        TextInputLayout discountLayout = (TextInputLayout) findViewById(R.id.layout_discount_createproduct);
        CheckBox newCheck = (CheckBox) findViewById(R.id.new_createproduct);
        CheckBox usedCheck = (CheckBox) findViewById(R.id.used_createproduct);
        Spinner categorySpinner = (Spinner) findViewById(R.id.spinner_category_createproduct);
        Spinner shipmentPlansSpinner = (Spinner) findViewById(R.id.spinner_shipmentplan_createproduct);
        Button createButton = (Button) findViewById(R.id.button_create_createproduct);
        Button clearButton = (Button) findViewById(R.id.button_cancel_createproduct);

        newCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    usedCheck.setChecked(false);
                }
            }
        });

        usedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    newCheck.setChecked(false);
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                nameLayout.getEditText().setText("");
                weightLayout.getEditText().setText("");
                priceLayout.getEditText().setText("");
                discountLayout.getEditText().setText("");
                usedCheck.setChecked(false);
                newCheck.setChecked(false);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Response.Listener<String> listener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject object = new JSONObject(response);
                            product = gson.fromJson(object.toString(),Product.class);
                            Toast.makeText(CreateProductActivity.this,"Produk Terdaftar!",Toast.LENGTH_SHORT).show();
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(CreateProductActivity.this,"Product gagal terdaftar!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        System.out.println(product);
                    }
                };
                CreateProductRequest requestProduct = new CreateProductRequest(nameLayout.getEditText().getText().toString(),weightLayout.getEditText().getText().toString(),String.valueOf(newCheck.isChecked()),priceLayout.getEditText().getText().toString(),discountLayout.getEditText().getText().toString(),categorySpinner.getSelectedItem().toString(),convertShipmentPlans(shipmentPlansSpinner.getSelectedItem().toString()),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
                requestQueue.add(requestProduct);
            }
        });
    }

    protected String convertShipmentPlans(String shipment)
    {
        switch (shipment)
        {
            case "INSTANT":
                return "0";
            case "SAME DAY":
                return "1";
            case "NEXT DAY":
                return "2";
            case "REGULER":
                return "3";
            default:
                return "4";
        }
    }
}