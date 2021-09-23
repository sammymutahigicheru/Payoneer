
package com.sammy.bestpractice.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Networks {

    @SerializedName("applicable")
    private List<Applicable> mApplicable;

    public List<Applicable> getApplicable() {
        return mApplicable;
    }

    public void setApplicable(List<Applicable> applicable) {
        mApplicable = applicable;
    }

}
