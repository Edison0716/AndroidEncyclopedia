package com.junlong0716.module.girls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyu on 2016/12/8.
 */

public class GirlsComingEvent {

    private List<MeiZi> girls;

    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public GirlsComingEvent(String from, List<MeiZi> girls) {
        this.girls = girls;
        this.from = from;
    }

    public GirlsComingEvent(String from, MeiZi girl) {
        this.girls = new ArrayList<>();
        girls.add(girl);
        this.from = from;
    }

    public List<MeiZi> getGirls() {
        return girls;
    }

    public void setGirls(List<MeiZi> girls) {
        this.girls = girls;
    }
}
