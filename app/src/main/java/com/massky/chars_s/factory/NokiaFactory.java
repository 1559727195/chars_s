package com.massky.chars_s.factory;

public class NokiaFactory extends Factory {
    @Override
    public <T extends NokiaPhone> T createNokia(Class<T> clz) {
        NokiaPhone nokiaPhone = null;
        try {
           // nokiaPhone = (NokiaPhone) Class.forName(clz.getName()).newInstance();
            nokiaPhone = (NokiaPhone) Class.forName(clz.getName()).newInstance();//反射
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) nokiaPhone;
    }
}