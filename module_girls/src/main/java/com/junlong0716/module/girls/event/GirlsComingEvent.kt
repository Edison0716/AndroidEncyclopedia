package com.junlong0716.module.girls.event

import com.junlong0716.module.girls.model.MeiZi
import java.util.ArrayList

/**
 * Created by yuyu on 2016/12/8.
 */

class GirlsComingEvent {
    var girls: ArrayList<MeiZi>
    var from: String

    constructor(from: String, girls: ArrayList<MeiZi>) {
        this.girls = girls
        this.from = from
    }

    constructor(from: String, girl: MeiZi) {
        this.girls = ArrayList()
        this.girls.add(girl)
        this.from = from
    }

//    fun getGirls():ArrayList<MeiZi>{
//        return this.girls
//    }
//
//    fun setGirls(girls: ArrayList<MeiZi>){
//        this.girls = girls
//    }

//    fun getForm():String{
//        return this.from
//    }
//
//    fun setForm(from: String){
//        this.from = from
//    }
}

//public class GirlsComingEvent {
//
//    private List<MeiZi> girls;
//
//    private String from;
//
//    public String getFrom() {
//        return from;
//    }
//
//    public void setFrom(String from) {
//        this.from = from;
//    }
//
//    public GirlsComingEvent(String from, List<MeiZi> girls) {
//        this.girls = girls;
//        this.from = from;
//    }
//
//    public GirlsComingEvent(String from, MeiZi girl) {
//        this.girls = new ArrayList<>();
//        girls.add(girl);
//        this.from = from;
//    }
//
//    public List<MeiZi> getGirls() {
//        return girls;
//    }
//
//    public void setGirls(List<MeiZi> girls) {
//        this.girls = girls;
//    }
//}
