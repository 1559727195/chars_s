package com.massky.chars_s.chain;



public class CTO extends Employee {

    public CTO() {
        this("技术经理", 5);
    }

    public CTO(String actor, int maxHandleDay) {
        super("技术经理", 5);
        setLeader(new PV());
    }

}