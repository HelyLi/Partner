package com.hhly.partner.data.net.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class BaseReq {

    private Integer country = 0;

    /**
     * 1.PC 2.安卓 3.ios 4.h5  5.其他
     */
    private Integer platformType = 2;
    /**
     * 1.PC 2.安卓 3.ios 4.h5  5.其他
     */
    private Integer terminalsType = 2;

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public Integer getPlatformType() {
        return platformType;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getCountry() {
        return country;
    }

    public Map<String, String> params() {

        Map<String, String> params = new HashMap<>();

        if (country != null) {
            params.put("country", String.valueOf(country));
        }
        if (platformType != null) {
            params.put("platformType", String.valueOf(platformType));
        }
        if (terminalsType != null){
            params.put("terminalsType", String.valueOf(terminalsType));
        }
        return params;
    }

    @Override
    public String toString() {
        return "BaseReq{" +
                "country=" + country +
                '}';
    }
}
