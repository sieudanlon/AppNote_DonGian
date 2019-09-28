package com.example.appnote_dongian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private ArrayList<CongViec> congViecArrayList;
    private int layout;
    public CongViecAdapter(MainActivity context,int layout, ArrayList<CongViec> congViecArrayList) {//alt+inser cua 2 cai phi tren
        this.context = context;
        this.congViecArrayList = congViecArrayList;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return congViecArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tv_ten;
        ImageView iv_delete,iv_edit;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_dongcongviec,null);
            holder.tv_ten = view.findViewById(R.id.tv_ten);
            holder.iv_delete = view.findViewById(R.id.iv_delete);
            holder.iv_edit = view.findViewById(R.id.iv_settings);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final  CongViec congViec = congViecArrayList.get(i);
        holder.tv_ten.setText(congViec.getTenCV());


        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogEdit(congViec.getTenCV(),congViec.getIdCV());
            }
        });

        return view;
    }
}
