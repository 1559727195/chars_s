package com.massky.chars_s.chain;


public abstract class Employee implements IHandler{

    private String actor;//员工角色名称
    private String name;//员工名称
    private int maxHandleDay;//最大可审批的请假天数（不包含最大天数）

    private Employee leader;//直属领导

    public Employee(String actor, int maxHandleDay) {
        super();
        this.actor = actor;
        this.maxHandleDay = maxHandleDay;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getMaxHandleDay() {
        return maxHandleDay;
    }

    public void setMaxHandleDay(int maxHandleDay) {
        this.maxHandleDay = maxHandleDay;
    }

    public boolean dispatchAsk(LeaveAskModel askModel) {
        if(onInterceptAsk(askModel)){
            return handleAsk(askModel);
        }else{
            if(getLeader()!=null){
                System.out.println(getActor()+"提交了了角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");
                return getLeader().dispatchAsk(askModel);
            }else{
                System.out.println(getActor()+"提交了了角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");

                //写这段代码的程序员进行审批 ，终于站在了食物链的顶端（原来是在做梦）。
                System.out.println("【代码作者】"+"拦截了角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");
                System.out.println("【代码作者】"+"拒绝了角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");
                return false;
            }
        }
    }

    public boolean onInterceptAsk(LeaveAskModel askModel) {

        if(askModel!=null&&!this.getClass().equals(askModel.getAskEmp().getClass())&&askModel.getDays()<this.getMaxHandleDay()){
            System.out.println(getActor()+"拦截了角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");
            return true;
        }
        return false;
    }

    public boolean handleAsk(LeaveAskModel askModel) {
        if(askModel==null||askModel.getAskEmp().getName().equals("小帥哥0")){
            System.out.println(getActor()+"已拒绝角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");
            return false;
        }
        if(askModel!=null&&askModel.getDays()<this.getMaxHandleDay()){
            System.out.println(getActor()+"已批准角色为【"+askModel.getAskEmp().actor+"】提交的休假请求；时长：【"+askModel.getDays()+"】天。");
            return true;
        }

        return false;
    }


    public Employee getLeader() {
        return leader;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
