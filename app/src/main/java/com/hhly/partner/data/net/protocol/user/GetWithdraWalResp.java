package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class GetWithdraWalResp extends BaseResp {


    /**
     * currentWithdrawalConf : {"id":186,"monthType":null,"balanceDate":"1","lowPrice":200,"updateUser":"mrdeepen","validTime":1490976000000,"invalidTime":null,"isNew":0,"recordId":128,"startDate1":"1","endDate1":"25","startDate2":null,"endDate2":null,"startDate3":null,"endDate3":null,"startDate4":null,"endDate4":null,"startDate5":null,"endDate5":null}
     * lastWithdrawalConf : {"id":186,"monthType":null,"balanceDate":"1","lowPrice":200,"updateUser":"mrdeepen","validTime":1490976000000,"invalidTime":null,"isNew":0,"recordId":128,"startDate1":"1","endDate1":"25","startDate2":null,"endDate2":null,"startDate3":null,"endDate3":null,"startDate4":null,"endDate4":null,"startDate5":null,"endDate5":null}
     */

    private CurrentWithdrawalConfBean currentWithdrawalConf;
    private LastWithdrawalConfBean lastWithdrawalConf;

    public CurrentWithdrawalConfBean getCurrentWithdrawalConf() {
        return currentWithdrawalConf;
    }

    public void setCurrentWithdrawalConf(CurrentWithdrawalConfBean currentWithdrawalConf) {
        this.currentWithdrawalConf = currentWithdrawalConf;
    }

    public LastWithdrawalConfBean getLastWithdrawalConf() {
        return lastWithdrawalConf;
    }

    public void setLastWithdrawalConf(LastWithdrawalConfBean lastWithdrawalConf) {
        this.lastWithdrawalConf = lastWithdrawalConf;
    }

    public static class CurrentWithdrawalConfBean {
        /**
         * id : 186
         * monthType : null
         * balanceDate : 1
         * lowPrice : 200
         * updateUser : mrdeepen
         * validTime : 1490976000000
         * invalidTime : null
         * isNew : 0
         * recordId : 128
         * startDate1 : 1
         * endDate1 : 25
         * startDate2 : null
         * endDate2 : null
         * startDate3 : null
         * endDate3 : null
         * startDate4 : null
         * endDate4 : null
         * startDate5 : null
         * endDate5 : null
         */

        private int id;
        private Object monthType;
        private String balanceDate;
        private int lowPrice;
        private String updateUser;
        private long validTime;
        private Object invalidTime;
        private int isNew;
        private int recordId;
        private String startDate1;
        private String endDate1;
        private String startDate2;
        private String endDate2;
        private String startDate3;
        private String endDate3;
        private String startDate4;
        private String endDate4;
        private String startDate5;
        private String endDate5;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getMonthType() {
            return monthType;
        }

        public void setMonthType(Object monthType) {
            this.monthType = monthType;
        }

        public String getBalanceDate() {
            return balanceDate;
        }

        public void setBalanceDate(String balanceDate) {
            this.balanceDate = balanceDate;
        }

        public int getLowPrice() {
            return lowPrice;
        }

        public void setLowPrice(int lowPrice) {
            this.lowPrice = lowPrice;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public long getValidTime() {
            return validTime;
        }

        public void setValidTime(long validTime) {
            this.validTime = validTime;
        }

        public Object getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(Object invalidTime) {
            this.invalidTime = invalidTime;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getStartDate1() {
            return startDate1;
        }

        public void setStartDate1(String startDate1) {
            this.startDate1 = startDate1;
        }

        public String getEndDate1() {
            return endDate1;
        }

        public void setEndDate1(String endDate1) {
            this.endDate1 = endDate1;
        }

        public String getStartDate2() {
            return startDate2;
        }

        public void setStartDate2(String startDate2) {
            this.startDate2 = startDate2;
        }

        public String getEndDate2() {
            return endDate2;
        }

        public void setEndDate2(String endDate2) {
            this.endDate2 = endDate2;
        }

        public String getStartDate3() {
            return startDate3;
        }

        public void setStartDate3(String startDate3) {
            this.startDate3 = startDate3;
        }

        public String getEndDate3() {
            return endDate3;
        }

        public void setEndDate3(String endDate3) {
            this.endDate3 = endDate3;
        }

        public String getStartDate4() {
            return startDate4;
        }

        public void setStartDate4(String startDate4) {
            this.startDate4 = startDate4;
        }

        public String getEndDate4() {
            return endDate4;
        }

        public void setEndDate4(String endDate4) {
            this.endDate4 = endDate4;
        }

        public String getStartDate5() {
            return startDate5;
        }

        public void setStartDate5(String startDate5) {
            this.startDate5 = startDate5;
        }

        public String getEndDate5() {
            return endDate5;
        }

        public void setEndDate5(String endDate5) {
            this.endDate5 = endDate5;
        }
    }

    public static class LastWithdrawalConfBean {
        /**
         * id : 186
         * monthType : null
         * balanceDate : 1
         * lowPrice : 200
         * updateUser : mrdeepen
         * validTime : 1490976000000
         * invalidTime : null
         * isNew : 0
         * recordId : 128
         * startDate1 : 1
         * endDate1 : 25
         * startDate2 : null
         * endDate2 : null
         * startDate3 : null
         * endDate3 : null
         * startDate4 : null
         * endDate4 : null
         * startDate5 : null
         * endDate5 : null
         */

        private int id;
        private Object monthType;
        private String balanceDate;
        private int lowPrice;
        private String updateUser;
        private long validTime;
        private Object invalidTime;
        private int isNew;
        private int recordId;
        private String startDate1;
        private String endDate1;
        private Object startDate2;
        private Object endDate2;
        private Object startDate3;
        private Object endDate3;
        private Object startDate4;
        private Object endDate4;
        private Object startDate5;
        private Object endDate5;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getMonthType() {
            return monthType;
        }

        public void setMonthType(Object monthType) {
            this.monthType = monthType;
        }

        public String getBalanceDate() {
            return balanceDate;
        }

        public void setBalanceDate(String balanceDate) {
            this.balanceDate = balanceDate;
        }

        public int getLowPrice() {
            return lowPrice;
        }

        public void setLowPrice(int lowPrice) {
            this.lowPrice = lowPrice;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public long getValidTime() {
            return validTime;
        }

        public void setValidTime(long validTime) {
            this.validTime = validTime;
        }

        public Object getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(Object invalidTime) {
            this.invalidTime = invalidTime;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getStartDate1() {
            return startDate1;
        }

        public void setStartDate1(String startDate1) {
            this.startDate1 = startDate1;
        }

        public String getEndDate1() {
            return endDate1;
        }

        public void setEndDate1(String endDate1) {
            this.endDate1 = endDate1;
        }

        public Object getStartDate2() {
            return startDate2;
        }

        public void setStartDate2(Object startDate2) {
            this.startDate2 = startDate2;
        }

        public Object getEndDate2() {
            return endDate2;
        }

        public void setEndDate2(Object endDate2) {
            this.endDate2 = endDate2;
        }

        public Object getStartDate3() {
            return startDate3;
        }

        public void setStartDate3(Object startDate3) {
            this.startDate3 = startDate3;
        }

        public Object getEndDate3() {
            return endDate3;
        }

        public void setEndDate3(Object endDate3) {
            this.endDate3 = endDate3;
        }

        public Object getStartDate4() {
            return startDate4;
        }

        public void setStartDate4(Object startDate4) {
            this.startDate4 = startDate4;
        }

        public Object getEndDate4() {
            return endDate4;
        }

        public void setEndDate4(Object endDate4) {
            this.endDate4 = endDate4;
        }

        public Object getStartDate5() {
            return startDate5;
        }

        public void setStartDate5(Object startDate5) {
            this.startDate5 = startDate5;
        }

        public Object getEndDate5() {
            return endDate5;
        }

        public void setEndDate5(Object endDate5) {
            this.endDate5 = endDate5;
        }
    }
}
