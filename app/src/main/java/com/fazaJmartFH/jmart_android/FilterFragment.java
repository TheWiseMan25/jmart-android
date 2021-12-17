package com.fazaJmartFH.jmart_android;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.*;
import com.google.android.material.textfield.TextInputLayout;
import com.fazaJmartFH.jmart_android.model.Product;

/**
 * FilterFragment Class
 *
 * Digunakan untuk menampilkan filter pada MainActivity. Class ini merupakan fragment.
 *
 * @author Nabil Mafaza
 * @version 0.1
 * @since 17-12-2021
 */
public class FilterFragment extends Fragment implements ProductFragment.ProductFragmentListener
{
    private FragmentManager mFragmentManager;
    private ProductFragment productFragment;

    ArrayAdapter<Product> listViewAdapterFilter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FilterFragment()
    {
    }

    public static FilterFragment newInstance(String param1, String param2)
    {
        FilterFragment fragment = new FilterFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        TextInputLayout filterName = (TextInputLayout) view.findViewById(R.id.name_filter);
        TextInputLayout filterLowestPrice = (TextInputLayout) view.findViewById(R.id.lowest_filter);
        TextInputLayout filterHighestPrice = (TextInputLayout) view.findViewById(R.id.highest_filter);
        CheckBox newProductCheck = (CheckBox) view.findViewById(R.id.new_filter);
        CheckBox usedProductCheck = (CheckBox) view.findViewById(R.id.used_filter);
        Spinner productCategorySpinner = (Spinner) view.findViewById(R.id.spinner_filter);
        Button applyButton = (Button)  view.findViewById(R.id.apply_filter);
        Button clearButton = (Button)  view.findViewById(R.id.clear_filter);
        return view;
    }

    @Override
    public void getProductList(ArrayAdapter<Product> listViewAdapter)
    {
        listViewAdapterFilter = listViewAdapter;
    }
}
