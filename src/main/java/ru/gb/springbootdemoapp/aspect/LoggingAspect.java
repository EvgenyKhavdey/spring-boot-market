package ru.gb.springbootdemoapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.gb.springbootdemoapp.dto.ControllerTime;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* ru.gb.springbootdemoapp.controller.ProductController.getAllStudents())")
    private void getProductController(){}

    @Pointcut("execution(* ru.gb.springbootdemoapp.controller.OrderController.getOrderPage())")
    private void getOrderController(){}

    @Pointcut("execution(* ru.gb.springbootdemoapp.controller.rest.CartRestController.getCart())")
    private void getCartRestController(){}

    @Pointcut("getProductController() || getOrderController() || getCartRestController()")
    private void getController(){}

    @Around("getController()")
    public Object aroundCurrentTimeController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        long end = System.currentTimeMillis();
        if (methodSignature.getName().equals("getAllStudents")){
            ControllerTime.productTime = end - begin;
        } else if (methodSignature.getName().equals("getOrderPage")){
            ControllerTime.orderTime = end - begin;
        } else if (methodSignature.getName().equals("getCart")){
            ControllerTime.cartTime = end - begin;
        }
        return obj;
    }
}
