package com.massky.chars_s.chain;


/**
 *
 * @author ziyuo
 * @Description 代表请假申请信息对象
 */
public class LeaveAskModel {
    private int days;// 休假天数
    private Employee askEmp;// 申请人

    public LeaveAskModel(int days, Employee askEmp) {
        super();
        this.days = days;
        this.askEmp = askEmp;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Employee getAskEmp() {
        return askEmp;
    }

    public void setAskEmp(Employee askEmp) {
        this.askEmp = askEmp;
    }

}