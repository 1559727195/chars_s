package com.massky.chars_s.chain;

public class CEO extends Employee {
    public CEO(){
        this("首席执行官", 120);
    }
    public CEO(String actor, int maxHandleDay) {
        super("首席执行官", 120);
        setLeader(null);
    }
}