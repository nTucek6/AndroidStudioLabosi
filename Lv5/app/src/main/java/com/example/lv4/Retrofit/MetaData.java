package com.example.lv4.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("count")
    private int count;
    @SerializedName("limit")
    private int limit;
    @SerializedName("offset")
    private int offset;
    @SerializedName("status")
    private String status;
    @SerializedName("total")
    private int total;

    public int getCount() {
        return count;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public String getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }
}
