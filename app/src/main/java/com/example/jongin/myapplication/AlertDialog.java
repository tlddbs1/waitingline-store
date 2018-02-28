package com.example.jongin.myapplication;

import android.content.Context;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jongin on 2018-02-27.
 */

public class AlertDialog extends Dialog{
    Context context;


    public AlertDialog(Context _context){
        super(_context);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE); // no show title bar
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.alert_modal);

        Button btn_popup_confirm = findViewById(R.id.btn_popup_confirm);
        btn_popup_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flag = findViewById(R.id.tv_alert).getTag().toString();
                String[] flagArr = flag.split(",");
                String msg = "";
                switch (flagArr[1]) {
                    case "S" :
                        msg = "착석";
                        break;
                    case "C" :
                        msg = "취소";
                        break;
                    case "A" :
                        msg = "부재";
                        break;
                }
                Toast.makeText(getContext(),flagArr[0] + ":" + msg,Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        Button btn_popup_cancel = findViewById(R.id.btn_popup_cancel);
        btn_popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "취소" , Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

    }
}
