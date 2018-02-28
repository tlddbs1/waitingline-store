package com.example.jongin.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ll_phone;
    LinearLayout ll_count;

    TextView tv_pad_0;
    TextView tv_pad_1;
    TextView tv_pad_2;
    TextView tv_pad_3;
    TextView tv_pad_4;
    TextView tv_pad_5;
    TextView tv_pad_6;
    TextView tv_pad_7;
    TextView tv_pad_8;
    TextView tv_pad_9;
    ImageView iv_pad_back;

    String numberFlag = "1";

    TextView tv_phone;
    TextView tv_phone_value;
    TextView tv_count;
    TextView tv_count_value;

    TextView tv_reset;

    TextView search_btn;
    TextView submit_btn;

    ListView listView;
    ImageView iv_reset;
    ListAdapter listAdapter;
    TextView tv_count_team;
    String str= "[" +
            "{'seq' : 1 , 'count' : 5 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 2 , 'count' : 10 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 3 , 'count' : 2 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 4 , 'count' : 3 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 5 , 'count' : 4 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 6 , 'count' : 1 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 7 , 'count' : 6 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 8 , 'count' : 9 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
            "{'seq' : 9 , 'count' : 5 , 'tel' : '010-1234-1234' , 'statusFlag' : 'S'}," +
            "{'seq' : 10 , 'count' : 3 , 'tel' : '010-1234-1234' , 'statusFlag' : 'S'}," +
            "]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll_phone = findViewById(R.id.ll_phone);
        ll_count = findViewById(R.id.ll_count);
        ll_phone.setOnClickListener(this);
        ll_count.setOnClickListener(this);

        tv_reset = findViewById(R.id.tv_reset);
        tv_reset.setOnClickListener(this);

        tv_pad_0 = findViewById(R.id.tv_pad_0);
        tv_pad_1 = findViewById(R.id.tv_pad_1);
        tv_pad_2 = findViewById(R.id.tv_pad_2);
        tv_pad_3 = findViewById(R.id.tv_pad_3);
        tv_pad_4 = findViewById(R.id.tv_pad_4);
        tv_pad_5 = findViewById(R.id.tv_pad_5);
        tv_pad_6 = findViewById(R.id.tv_pad_6);
        tv_pad_7 = findViewById(R.id.tv_pad_7);
        tv_pad_8 = findViewById(R.id.tv_pad_8);
        tv_pad_9 = findViewById(R.id.tv_pad_9);
        iv_pad_back = findViewById(R.id.iv_pad_back);

        search_btn = findViewById(R.id.search_btn);
        submit_btn = findViewById(R.id.submit_btn);

        tv_pad_0.setOnClickListener(this);

        tv_pad_1.setOnClickListener(this);
        tv_pad_2.setOnClickListener(this);
        tv_pad_3.setOnClickListener(this);
        tv_pad_4.setOnClickListener(this);
        tv_pad_5.setOnClickListener(this);
        tv_pad_6.setOnClickListener(this);
        tv_pad_7.setOnClickListener(this);
        tv_pad_8.setOnClickListener(this);
        tv_pad_9.setOnClickListener(this);
        iv_pad_back.setOnClickListener(this);

        search_btn.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        tv_phone = findViewById(R.id.tv_phone);
        tv_phone_value = findViewById(R.id.tv_phone_value);
        tv_count = findViewById(R.id.tv_count);

        tv_count_value = findViewById(R.id.tv_count_value);

        listView = findViewById(R.id.waiting_list);

        iv_reset = findViewById(R.id.iv_reset);
        iv_reset.setOnClickListener(this);
        //String[] telNum = {"010-4722-6209" , "010-1234-1234" , "010-3333-3333" , "010-4501-1923", "010-1234-1234" , "010-3333-3333" , "010-4501-1923", "010-1234-1234" , "010-3333-3333" , "010-4501-1923", "010-1234-1234" , "010-3333-3333" , "010-4501-1923"};

        int seq = 0;
        ArrayList<Waiting> waitingList = new ArrayList<>();
        /*
        for(int i =0 ; i < telNum.length ; i++){
            Waiting waiting = new Waiting();
            waiting.count = i+1;
            waiting.tel = telNum[i];
            waitingList.add(waiting);
            if(seq >= telNum.length) seq = 0;
        }*/
        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i=0; i < jsonArray.length() ; i++){
                JSONObject jobj = jsonArray.getJSONObject(i);
                Waiting oWaiting = new Waiting();
                oWaiting.setSeq(jobj.getInt("seq"));
                oWaiting.setCount(jobj.getInt("count"));
                oWaiting.setTel(jobj.getString("tel"));
                oWaiting.setStatusFlag(jobj.getString("statusFlag"));
                waitingList.add(oWaiting);
                if("W".equals(jobj.getString("statusFlag"))){
                    seq += 1;
                }
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        tv_count_team = findViewById(R.id.tv_count_team);
        tv_count_team.setText(seq+"");
        listView.setDivider(null);

        listAdapter = new ListAdapter(this , waitingList);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_phone:
                numberFlag = "1";
                inputAreaToggle(numberFlag);
                break;
            case R.id.ll_count:
                numberFlag = "2";
                inputAreaToggle(numberFlag);
                break;
            case R.id.tv_reset:
                reset();
                break;
            case R.id.iv_pad_back:
                if("1".equals(numberFlag)){
                    inputPhoneNumber("back");
                }else{
                    inputCount("back");
                }
                break;
            case R.id.iv_reset:
                listReset();
                break;
            case R.id.submit_btn:
                Toast.makeText(this, "등록버튼이벤트" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_btn:
                Toast.makeText(this, "검색버튼이벤트" , Toast.LENGTH_SHORT).show();
                break;
            default :
                TextView tv = findViewById(v.getId());
                if("1".equals(numberFlag)){
                    inputPhoneNumber(tv.getText().toString());
                }else{
                    inputCount(tv.getText().toString());
                }
        }
    }

    public void inputAreaToggle(String numberFlag){


        if ( "1".equals(numberFlag)){
            ll_phone.setBackgroundColor(Color.parseColor("#585858"));
            ll_count.setBackgroundColor(Color.parseColor("#b1b1b1"));
            tv_phone.setTextColor(Color.parseColor("#959595"));
            tv_phone_value.setTextColor(Color.parseColor("#959595"));
            tv_count.setTextColor(Color.parseColor("#dbdbdb"));
            tv_count_value.setTextColor(Color.parseColor("#dbdbdb"));
        }else{
            ll_phone.setBackgroundColor(Color.parseColor("#b1b1b1"));
            ll_count.setBackgroundColor(Color.parseColor("#585858"));
            tv_phone.setTextColor(Color.parseColor("#dbdbdb"));
            tv_phone_value.setTextColor(Color.parseColor("#dbdbdb"));
            tv_count.setTextColor(Color.parseColor("#959595"));
            tv_count_value.setTextColor(Color.parseColor("#959595"));
        }
    }

    public void inputPhoneNumber(String value){
        TextView tv = findViewById(R.id.tv_phone_value);
        String phoneNumber = tv.getText().toString();
        if(!"back".equals(value)){
            phoneNumber = phoneNumber +  value;
            tv.setText(phoneNumber);
        }else{
            if(phoneNumber.length() > 0){
                phoneNumber = phoneNumber.substring(0, phoneNumber.length()-1);
                tv.setText(phoneNumber);
            }
        }
    }
    public void inputCount(String value){
        TextView tv = findViewById(R.id.tv_count_value);
        String countNumber = tv.getText().toString();
        if(!"back".equals(value)){
            countNumber = countNumber +  value;
            tv.setText(countNumber);
        }else{
            if(countNumber.length() > 0){
                countNumber = countNumber.substring(0, countNumber.length()-1);
                tv.setText(countNumber);
            }
        }
    }
    public void reset(){
        TextView tv_count = findViewById(R.id.tv_count_value);
        TextView tv_phone = findViewById(R.id.tv_phone_value);
        tv_count.setText("");
        tv_phone.setText("");
    }
    public void listReset(){
        str= "[" +
                "{'seq' : 1 , 'count' : 5 , 'tel' : '010-1234-4444' , 'statusFlag' : 'W'}," +
                "{'seq' : 2 , 'count' : 10 , 'tel' : '010-1234-4444' , 'statusFlag' : 'W'}," +
                "{'seq' : 3 , 'count' : 2 , 'tel' : '010-1234-1111' , 'statusFlag' : 'W'}," +
                "{'seq' : 4 , 'count' : 3 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
                "{'seq' : 5 , 'count' : 4 , 'tel' : '010-1234-2121' , 'statusFlag' : 'W'}," +
                "{'seq' : 6 , 'count' : 1 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
                "{'seq' : 7 , 'count' : 6 , 'tel' : '010-1234-1234' , 'statusFlag' : 'W'}," +
                "{'seq' : 8 , 'count' : 9 , 'tel' : '010-1234-1234' , 'statusFlag' : 'S'}," +
                "{'seq' : 9 , 'count' : 5 , 'tel' : '010-1234-2424' , 'statusFlag' : 'S'}," +
                "{'seq' : 10 , 'count' : 3 , 'tel' : '010-1234-2424' , 'statusFlag' : 'S'}," +
                "]";
        Toast.makeText(this, "리스트 리셋버튼" , Toast.LENGTH_SHORT).show();
        int seq = 0;
        ArrayList<Waiting> waitingList = new ArrayList<>();
        /*
        for(int i =0 ; i < telNum.length ; i++){
            Waiting waiting = new Waiting();
            waiting.count = i+1;
            waiting.tel = telNum[i];
            waitingList.add(waiting);
            if(seq >= telNum.length) seq = 0;
        }*/
        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i=0; i < jsonArray.length() ; i++){
                JSONObject jobj = jsonArray.getJSONObject(i);
                Waiting oWaiting = new Waiting();
                oWaiting.setSeq(jobj.getInt("seq"));
                oWaiting.setCount(jobj.getInt("count"));
                oWaiting.setTel(jobj.getString("tel"));
                oWaiting.setStatusFlag(jobj.getString("statusFlag"));
                waitingList.add(oWaiting);
                if("W".equals(jobj.getString("statusFlag"))){
                    seq += 1;
                }
            }
            tv_count_team.setText("7");
        }catch (Exception e){
            e.getStackTrace();
        }
        listAdapter = new ListAdapter(this , waitingList);
        listView.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();
    }
}
