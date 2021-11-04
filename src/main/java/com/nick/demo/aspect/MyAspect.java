package com.nick.demo.aspect;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configurable
public class MyAspect {
    private static final Logger log= LoggerFactory.getLogger(MyAspect.class);

    @Autowired
    Tracer tracer;
    @Value("${spring.application.name}")
    String appName;

    @Pointcut("within(com.nick.demo.model..*) && execution(public * *(..))")
    public void point1(){

    }
    @Pointcut("within(com.nick.demo.service..*) && execution(public * *(..))")
    public void point2(){

    }
    @Pointcut("execution(public * com.nick.demo.service2.IRoleService+.*(..))")
    public void point3(){

    }
    @Pointcut("execution(public * com.nick.demo.api..*.*(..))")
    public void point4(){

    }
    @Around("point1() || point2() ||point3() ||point4()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        Span span=null;
        Scope scope=null;

        try{
            if(tracer!=null){
                Object object=point.getTarget();
                JoinPoint.StaticPart staticPart=point.getStaticPart();
                String clazz=null;
                String method=null;
                if(object!=null){
                    clazz=object.getClass().getName();
                    method=point.getSignature().getName();
                }else if(staticPart!=null){
                    clazz=staticPart.getSourceLocation().getFileName();
                    method=staticPart.getSignature().getName();
                }
                //if can not dermine class/method, skip
                if(clazz==null){
                    return point.proceed();
                }

                Span parent=tracer.activeSpan();
                Tracer.SpanBuilder spanBuilder=tracer.buildSpan(clazz+"."+method)
                        .withTag(Tags.COMPONENT.getKey(),appName)
                        .withTag("class",clazz)
                        .withTag("method",method);
                try{
                    spanBuilder.withTag("args", Arrays.toString(point.getArgs()));
                }catch (Throwable t){
                    //log.warn
                    log.warn("",t);

                }
                if(parent!=null){
                    spanBuilder.asChildOf(parent);
                }
                span=spanBuilder.start();
            }
            if(span!=null&&tracer!=null){
                scope=tracer.scopeManager().activate(span);
            }
            return point.proceed();
        }catch (Throwable t){
            if(span!=null){
                Map extInf=new HashMap();
                extInf.put("event",Tags.ERROR.getKey());
                extInf.put("error.exception",t);
                span.log(extInf);
                Tags.ERROR.set(span,true);
            }
            throw  t;
        }finally {
            if(scope!=null){
                scope.close();
            }
            if(span!=null){
                span.finish();
            }
        }
    }
}
