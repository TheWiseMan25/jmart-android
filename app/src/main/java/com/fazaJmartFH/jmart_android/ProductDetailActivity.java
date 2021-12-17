package com.fazaJmartFH.jmart_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * ProductDetailActivity Class
 *
 * Digunakan untuk menampilkan halaman detail produk.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class ProductDetailActivity extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView productName = (TextView) findViewById(R.id.namaproduk);
        TextView productPrice = (TextView) findViewById(R.id.hargaproduk);
        TextView productCategory = (TextView) findViewById(R.id.category);
        TextView productCondition = (TextView) findViewById(R.id.condition);
        TextView productWeight = (TextView) findViewById(R.id.weight);
        TextView productShipment = (TextView) findViewById(R.id.shipmentplan);
        Button buyButton = (Button) findViewById(R.id.buy_button);

        productName.setText(ProductFragment.productClicked.name);

        String translatedCondition = "";
        if(ProductFragment.productClicked.conditionUsed)
        {
            translatedCondition = "New";
        }
        else
            {
            translatedCondition = "Used";
        }

        productPrice.setText("Rp. " + String.valueOf(ProductFragment.productClicked.price));
        productCategory.setText(String.valueOf(ProductFragment.productClicked.category));
        productCondition.setText(translatedCondition);
        productWeight.setText(String.valueOf(ProductFragment.productClicked.weight));

        int shipmentPlansValue = ProductFragment.productClicked.shipmentPlans;
        switch (shipmentPlansValue)
        {
            case 0:
                productShipment.setText("INSTANT");
                break;
            case 1:
                productShipment.setText("SAME DAY");
                break;
            case 2:
                productShipment.setText("NEXT DAY");
                break;
            case 3:
                productShipment.setText("REGULER");
                break;
            case 4:
                productShipment.setText("KARGO");
                break;
            default:
                productShipment.setText("UNKNOWN");
        }

        buyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ProductDetailActivity.this, "BELI", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}