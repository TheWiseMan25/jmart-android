package com.fazaJmartFH.jmart_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import com.fazaJmartFH.jmart_android.model.Product;
import com.fazaJmartFH.jmart_android.request.RequestFactory;

public class ProductFragment extends Fragment
{

    ProductFragmentListener fragmentListener;
    ArrayAdapter<Product> listViewAdapter;
    final int pageSize = 20;
    static int page = 0;
    private static final Gson gson = new Gson();
    public static ArrayList<Product> productsList = new ArrayList<>();
    public static Product productClicked = null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProductFragment()
    {
    }

    public static ProductFragment newInstance(String param1, String param2)
    {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public interface ProductFragmentListener
    {
        void getProductList(ArrayAdapter<Product> listViewAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View productView = inflater.inflate(R.layout.fragment_product,container,false);

        Button prevButton = (Button) productView.findViewById(R.id.button_previous);
        Button nextButton = (Button) productView.findViewById(R.id.button_next);
        Button goButton = (Button) productView.findViewById(R.id.button_go);
        EditText inputPage = (EditText) productView.findViewById(R.id.input_halaman);
        Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray object = new JSONArray(response);
                    if(object != null){
                        productsList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Product>>(){}.getType()); // line 6
                        System.out.println(productsList);

                        listViewAdapter = new ArrayAdapter<Product>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, productsList)
                        {
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent)
                            {
                                View view = super.getView(position, convertView, parent);
                                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                                text1.setText(productsList.get(position).name);
                                text2.setText("Rp " + String.valueOf(productsList.get(position).price));
                                return view;
                            }
                        };
                        ListView listView = (ListView) productView.findViewById(R.id.list_view);
                        listView.setAdapter(listViewAdapter);
                        fragmentListener.getProductList(listViewAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                                productClicked = (Product) listView.getItemAtPosition(i);
                                Toast.makeText(getActivity(),"Produk terpilih", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProductFragment.this.getActivity(), ProductDetailActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(),"Halaman selanjutnya", Toast.LENGTH_SHORT).show();
                page += 1;
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(),"Halaman sebelumnya", Toast.LENGTH_SHORT).show();
                page -= 1;
                if(page < 0){
                    page = 0;
                }
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        goButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getContext(),"Berpindah halaman ...", Toast.LENGTH_SHORT).show();
                page = Integer.parseInt(inputPage.getText().toString()) - 1;
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(RequestFactory.getPage("product",page,pageSize,listener,null));

        return productView;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        if (context instanceof ProductFragmentListener)
        {
            fragmentListener = (ProductFragmentListener) context;
        }
        else
            {
            throw new RuntimeException(context.toString() + " must implement ProductFragmentListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        fragmentListener = null;
    }
}