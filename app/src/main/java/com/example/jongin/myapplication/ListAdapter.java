package com.example.jongin.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jongin on 2018-02-26.
 */

public class ListAdapter  extends BaseAdapter{
    Context context;
    ArrayList<Waiting> waitingList = new ArrayList<>();
    AlertDialog ad;

    public ListAdapter(Context _context , ArrayList<Waiting> waitingList){
        this.context = _context;
        this.waitingList = waitingList;
    }

    @Override
    public int getCount() {
        return waitingList.size();
    }

    @Override
    public Object getItem(int position) {
        return waitingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.waiting_list_view, null);
        TextView t1 = convertView.findViewById(R.id.tv_tel_number);
        TextView t2 = convertView.findViewById(R.id.tv_count);
        TextView tIndex = convertView.findViewById(R.id.list_seq);

        t1.setText(waitingList.get(position).getTel());
        t2.setText(waitingList.get(position).getCount()+"");
        int index = position+1;
        tIndex.setText((index)+"");

        TextView btn_seat = convertView.findViewById(R.id.btn_seat);
        TextView btn_cancel = convertView.findViewById(R.id.btn_cancel);
        TextView btn_absence = convertView.findViewById(R.id.btn_absence);
        btn_seat.setTag(waitingList.get(position).getSeq());
        btn_cancel.setTag(waitingList.get(position).getSeq());
        btn_absence.setTag(waitingList.get(position).getSeq());
        if("S".equals(waitingList.get(position).getStatusFlag())){
            btn_seat.setBackgroundColor(Color.parseColor("#c1e2cc"));
            btn_cancel.setBackgroundColor(Color.parseColor("#e4cdc1"));
            btn_absence.setBackgroundColor(Color.parseColor("#bed3e6"));
        }

        btn_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seat(v.getTag().toString());
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v.getTag().toString());
            }
        });
        btn_absence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                absence(v.getTag().toString());
            }
        });

        ad = new AlertDialog(context);

        WindowManager.LayoutParams wm = ad.getWindow().getAttributes();
        wm.copyFrom(ad.getWindow().getAttributes());

        return convertView;
    }

    public void seat(String seq){
        View view = LayoutInflater.from(context).inflate(R.layout.alert_modal, null);
        TextView tv = ad.getWindow().findViewById(R.id.tv_alert);
        tv.setText(seq+"번 고객이 착석하셨습니까?");
        tv.setTag(seq + ",S");
        ad.show();
    }
    public void cancel(String seq){
        View view = LayoutInflater.from(context).inflate(R.layout.alert_modal, null);
        TextView tv = ad.getWindow().findViewById(R.id.tv_alert);
        tv.setText(seq+"번 고객이 취소하셨습니까?");
        tv.setTag(seq + ",C");
        ad.show();
    }
    public void absence(String seq){
        View view = LayoutInflater.from(context).inflate(R.layout.alert_modal, null);
        TextView tv = ad.getWindow().findViewById(R.id.tv_alert);
        tv.setText(seq+"번 고객이 부재중이십니까?");
        tv.setTag(seq + ",A");
        ad.show();
    }
}
