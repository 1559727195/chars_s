package com.massky.chars_s;

/**
 * 工厂方法模式_email产品
 *
 * @author popkidorc
 *
 */
public class MyMessageEmail extends MyAbstractMessage {

    @Override
    public void sendMesage() throws Exception {
        // TODO Auto-generated method stub
        if (null == getMessageParam() || null == getMessageParam().get("EMAIL")
                || "".equals(getMessageParam().get("EMAIL"))) {
            throw new Exception("发送短信,需要传入EMAIL参数");// 为了简单起见异常也不自定义了
        }// 另外邮件内容，以及其他各种协议参数等等都要处理

        System.out.println("我是邮件，发送通知给" + getMessageParam().get("EMAIL"));
    }

}