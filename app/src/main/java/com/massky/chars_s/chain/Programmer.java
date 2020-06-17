package com.massky.chars_s.chain;



public class Programmer extends Employee {
    public Programmer() {
        this("程序员", 0);
    }

    public Programmer(String actor, int maxHandleDay) {
        super("程序员", 0);
        setLeader(new TeamLeader());
    }

    public boolean dispatchAsk(LeaveAskModel askModel) {
        return super.dispatchAsk(askModel);
    }

    public boolean onInterceptAsk(LeaveAskModel askModel) {
        return !(askModel.getAskEmp() instanceof Programmer);
    }

}
