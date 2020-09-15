package com.example.wheathergooglemap.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheathergooglemap.R;
import com.example.wheathergooglemap.model.City;
import com.example.wheathergooglemap.ui.activity.MainActivity;
import com.example.wheathergooglemap.ui.fragment.MapsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.Holder> {

    private List<City> mList;
    private AppCompatActivity mContext;

    public CityListAdapter(AppCompatActivity context, List<City> list) {
        this.mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        System.out.println("------------------------------------------" + mList.size());
        holder.textView.setText(mList.get(position).getName());

        if(position % 2 == 0)
            holder.imageView.setImageResource(R.drawable.temp3);
        else
            holder.imageView.setImageResource(R.drawable.temp1);

        holder.mLinearLayout.setOnClickListener(view -> {
            MapsFragment fragment = new MapsFragment(3, mList.get(position));
            /*Bundle bundle = new Bundle();
            bundle.putSerializable(MapsFragment.EXTRA_CITY_COORD, mList.get(position));
            fragment.setArguments(bundle);*/
            FragmentManager fm = mContext.getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.include_fragment, fragment).commit();
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateList(List<City> list) {
        this.mList = list;
        notifyDataSetChanged();
    }


    public static class Holder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_layout)
        LinearLayout mLinearLayout;
        @BindView(R.id.image_item)
        ImageView imageView;
        @BindView(R.id.text_item)
        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
