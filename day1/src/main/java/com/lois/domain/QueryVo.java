package com.lois.domain;

import java.io.Serializable;
import java.util.List;

public class QueryVo implements Serializable {
    private List<Integer> ages;

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        this.ages = ages;
    }
}
