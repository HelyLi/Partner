package com.hhly.partner.data.net.protocol;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class BaseResp<T> {

    protected int result;
    protected String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOk() {
        return result == 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResp{");
        sb.append("result=").append(result);
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
