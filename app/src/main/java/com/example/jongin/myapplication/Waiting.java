package com.example.jongin.myapplication;

/**
 * Created by jongin on 2018-02-26.
 */

public class Waiting {
    private int seq;
    private int count;
    private String tel;
    private String statusFlag;

    public void setSeq(int seq){
        this.seq = seq;
    }
    public int getSeq(){
        return seq;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    public String getTel(){
        return tel;
    }
    public void setStatusFlag(String statusFlag){
        this.statusFlag = statusFlag;
    }
    public String getStatusFlag(){
        return statusFlag;
    }
}
