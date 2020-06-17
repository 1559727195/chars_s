package com.massky.chars_s.chain;



public class TeamLeader extends Employee {

    public TeamLeader() {
        this("项目组长", 3);
    }
    public TeamLeader(String actor, int maxHandleDay) {
        super("项目组长", 3);
        setLeader(new CTO());
    }

}