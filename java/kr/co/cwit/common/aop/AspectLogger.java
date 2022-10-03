package kr.co.cwit.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLogger {
	private static final Logger log = LoggerFactory.getLogger("ASPECT_LOGGER");
	
	@Pointcut("execution(* kr.co.cwit..*(..))")
	public void projectTargetMethod() {
		// pointcut annotation 값을 참조하기 위한 dummy method
	}
	
	@Pointcut("execution(* kr.co.cwit.application..*ServiceImpl.*(..))")
	public void applicationTargetMethod() {
		// pointcut annotation 값을 참조하기 위한 dummy method
	}
	
	@Pointcut("execution(* kr.co.cwit.common..*ServiceImpl.*(..))")
	public void commonTargetMethod() {
		// pointcut annotation 값을 참조하기 위한 dummy method
	}
	
	@Pointcut("execution(* kr.co.cwit.common.scheduling.*.*(..))")
	public void schedulingTargetMethod() {
		// pointcut annotation 값을 참조하기 위한 dummy method
	}
	
	@Pointcut("execution(* kr.co.cwit.common.util.Email*.*(..)) || execution(* kr.co.cwit.common.util.*Mail.*(..))")
	public void emailSenderTargetMethod() {
		// pointcut annotation 값을 참조하기 위한 dummy method
	}
	
	@AfterThrowing(pointcut = "projectTargetMethod()", throwing = "exception")
	public void afterThrowingTargetMethod(JoinPoint thisJoinPoint, Exception exception) throws Exception {
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = thisJoinPoint.getSignature().getName();
        
		log.error("[{}.{}] exception 발생", className, methodName);
		log.error("exception = {}", exception);
	}

	@Around("applicationTargetMethod() || commonTargetMethod() || schedulingTargetMethod() || emailSenderTargetMethod()")
	public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = thisJoinPoint.getSignature().getName();
        
        log.info("[{}.{}] 호출", className, methodName);
        log.info("params = {}", thisJoinPoint.getArgs());
        
		long time1 = System.currentTimeMillis();
		Object retVal = thisJoinPoint.proceed();
		long time2 = System.currentTimeMillis();
		log.info("[{}.{}] 소요시간[{}ms]", className, methodName, (time2 - time1));

		return retVal;
	}
}
