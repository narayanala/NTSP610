package com.nt.advice;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class AroundLoggingAdvice implements MethodInterceptor {
	private static Logger logger;
	static {
		PropertyConfigurator.configure("src/main/java/com/nt/commons/log4j.properties");
	  logger=Logger.getLogger(AroundLoggingAdvice.class);	
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object retVal=null;
		Object args[]=null;
		logger.debug("Entering into "+invocation.getMethod().getName()+" with args"+Arrays.toString(invocation.getArguments()));
		//get and modifying argument values
		args=invocation.getArguments();
		if( ((Float)args[0])<=50000 )
			   args[2]=((Float)args[2])-0.5f;
		
		if(((Float)args[0])<=0 || ((Float)args[1])<=0 || ((Float)args[2])<=0)
			throw new IllegalArgumentException("Invalid inputs");

		retVal=invocation.proceed();
		logger.debug("Exiting from "+invocation.getMethod().getName()+" with args"+Arrays.toString(invocation.getArguments()));
		
		//modifying return value
		retVal=((Float)retVal)+((Float)retVal) *0.01f;
		return retVal;
	}

}
