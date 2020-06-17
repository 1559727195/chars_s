package com.massky.chars_s.chain;



/**
 *
 * @author ziyuo
 * @Description 抽象出来的休假申请处理接口
 */
public interface IHandler {

    /**
     * 分发请假请求
     * @param askModel
     * @return 分发结果
     */
    boolean dispatchAsk(LeaveAskModel askModel);

    /**
     * 拦截请假请求
     * @param askModel
     * @return 拦截与否
     */
    boolean onInterceptAsk(LeaveAskModel askModel);
    /**
     * 处理请假请求
     * @param askModel
     * @return 是否批准
     */
    boolean handleAsk(LeaveAskModel askModel);

}