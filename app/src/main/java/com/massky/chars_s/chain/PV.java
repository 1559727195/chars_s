package com.massky.chars_s.chain;




public class PV extends Employee {

    public PV(){
        this("部门经理", 10);
    }
    public PV(String actor, int maxHandleDay) {
        super("部门经理", 10);
        setLeader(new CEO());
    }

}