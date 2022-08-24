package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
// @Component: 해당 클래스를 빈등록 하는 간단한 방법 -> 서비스 패키지의 SpringConfig에 작성하여도 된다.
//@Component
public class TimeTraceAop {

//  해당 AOP을 어느 부분에 적용할지 선택 -> 여기서는 hello.hellospring 모든 메소드
//  보통 패키지 레밸로 적용하고 원할 경우 검색해서 사용법을 검색해서 사용
//    @Around("execution(* hello.hellospring.service..*(..))")
//    @Around("execution(* hello.hellospring..*(..))")

//    SpringConfig를 통해서 빈 등록을 할 경우 순환참조문제가 발생하여 아래와 같이 AOP대상에서 SpringConfig를 제외시켜준다.
//    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.service.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

//      현재 진행중인 메서드를 확인
        System.out.println("START: " + joinPoint.toString());
        try {
//          다음 메서드로 진행
            Object result = joinPoint.proceed();
            return result;

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
