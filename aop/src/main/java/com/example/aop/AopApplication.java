package com.example.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class AopApplication {

    public static void main(String[] args) {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return new ClassFilter() {
                    public boolean matches(Class<?> clazz) {
                        return clazz.getSimpleName().startsWith("HelloT");
                    }
                };
            }
        };
        nameMatchMethodPointcut.setMappedName("sayH*");

        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvisor(new DefaultPointcutAdvisor(nameMatchMethodPointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();
        System.out.println(proxiedHello.sayHello("Toby")); //"HELLO TOBY"
        System.out.println(proxiedHello.sayHi("Toby")); //"HI TOBY"
        System.out.println(proxiedHello.sayThankYou("Toby")); //"Thank You Toby"
    }
}
