package com.example.wheathergooglemap.ui.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.wheathergooglemap.MyApp;
import com.example.wheathergooglemap.R;
import com.example.wheathergooglemap.model.City;
import com.example.wheathergooglemap.ui.adapter.CityListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CityListFragment extends Fragment {

    private List<City> list;
    private CityListAdapter adapter;

    @BindView(R.id.city_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.etSearchBar)
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        ButterKnife.bind(this, view);
        list = MyApp.getCityList();

        setRecyclerView();

        //search editText
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //autogenerate
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //autogenerate
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateSearch(editable.toString());
            }
        });
        return view;
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CityListAdapter((AppCompatActivity) getContext(), list);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void updateSearch(String text) {
        List<City> temp = new ArrayList<>();
        for (City c : list) {
            if (c.getName().toLowerCase().contains(text.toLowerCase())) {
                temp.add(c);
            }
        }
        adapter.updateList(temp);
    }
}