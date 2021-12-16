package com.fazaJmartFH.jmart_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import com.fazaJmartFH.jmart_android.model.Payment;
import com.fazaJmartFH.jmart_android.request.PaymentRequest;

public class PaymentActivity extends AppCompatActivity
{

    private static final Gson gson = new Gson();
    private static Payment payment = null;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        TextInputLayout inputAddress = (TextInputLayout) findViewById(R.id.shipmentaddress_input);
        TextView nameDetail = (TextView) findViewById(R.id.name_detail);
        TextView weightDetail = (TextView) findViewById(R.id.weight_detail);
        TextView shipmentPlanDetail = (TextView) findViewById(R.id.shipmentplan_detail);
        TextView priceDetail = (TextView) findViewById(R.id.price_detail);
        Button returnButton = (Button) findViewById(R.id.button_return);
        Button proceedButton = (Button) findViewById(R.id.button_proceed);

        nameDetail.setText(ProductFragment.productClicked.name);

        int shipmentPlansValue = ProductFragment.productClicked.shipmentPlans;
        switch (shipmentPlansValue)
        {
            case 0:
                shipmentPlanDetail.setText("INSTANT");
                break;
            case 1:
                shipmentPlanDetail.setText("SAME DAY");
                break;
            case 2:
                shipmentPlanDetail.setText("NEXT DAY");
                break;
            case 3:
                shipmentPlanDetail.setText("REGULER");
                break;
            case 4:
                shipmentPlanDetail.setText("KARGO");
                break;
            default:
                shipmentPlanDetail.setText("UNKNOWN");
        }

        weightDetail.setText(String.valueOf(ProductFragment.productClicked.weight));
        priceDetail.setText(String.valueOf(ProductFragment.productClicked.price));

        proceedButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject object = new JSONObject(response);
                            LoginActivity.getLoggedAccount().balance -= ProductFragment.productClicked.price;
                            payment = gson.fromJson(object.toString(), Payment.class);
                            Toast.makeText(PaymentActivity.this,"Silakan melakukan pembayaran",Toast.LENGTH_SHORT).show();

                        }catch (JSONException e)
                        {
                            Toast.makeText(PaymentActivity.this,"Ada eror",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        System.out.println(payment);
                    }
                };
                PaymentRequest paymentRequest = new PaymentRequest(LoginActivity.getLoggedAccount().id, ProductFragment.productClicked.id, 1 , inputAddress.getEditText().getText().toString(), ProductFragment.productClicked.shipmentPlans, listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add(paymentRequest);
            }
        });
    }
}