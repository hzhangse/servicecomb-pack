package org.apache.servicecomb.pack.demo.hotel;

import java.lang.reflect.Method;

import org.apache.servicecomb.pack.omega.context.CallbackContext;
import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;
import org.apache.servicecomb.pack.omega.transaction.spring.CallbackType;
import org.apache.servicecomb.pack.omega.transaction.spring.MethodCheckingCallback;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


public class IotBeanProcessor implements BeanPostProcessor, ApplicationContextAware {

	private ApplicationContext applicationContext;
	private final OmegaContext omegaContext;

	private final CallbackContext compensationContext;

	IotBeanProcessor(OmegaContext omegaContext, CallbackContext compensationContext) {
		this.omegaContext = omegaContext;
		this.compensationContext = compensationContext;
	}

	@Override
	@Nullable
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// if (beanName.equals("messageHandler")) {
		// DefaultListableBeanFactory defaultListableBeanFactory =
		// (DefaultListableBeanFactory)this.applicationContext.getAutowireCapableBeanFactory();
		// defaultListableBeanFactory.removeBeanDefinition("messageHandler");
		// return this.applicationContext.getBean("iotbullCompensationMessageHandler");
		//
		// }
		return bean;
	}

	@Override
	@Nullable
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		 checkMethod(bean);
		if (beanName.equals("messageHandler")) {
			DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) this.applicationContext
					.getAutowireCapableBeanFactory();
			defaultListableBeanFactory.removeBeanDefinition("messageHandler");
			return this.applicationContext.getBean("iotbullCompensationMessageHandler");

		}
		return bean;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	private void checkMethod(Object bean) {
		ReflectionUtils.doWithMethods(bean.getClass(),
				new CompensableMethodCheckingCallback(bean, compensationContext));
	}

	
	
	
	class CompensableMethodCheckingCallback extends MethodCheckingCallback {

		  public CompensableMethodCheckingCallback(Object bean, CallbackContext callbackContext) {
		    super(bean, callbackContext, CallbackType.Compensation);
		  }

		  @Override
		  public void doWith(Method method) throws IllegalArgumentException {
		    if (!method.isAnnotationPresent(Compensable.class)) {
		      return;
		    }
		    Compensable compensable = method.getAnnotation(Compensable.class);
		    String compensationMethod = compensable.compensationMethod();
		    // we don't support the retries number below -1.
		    if (compensable.retries() < -1) {
		      throw new IllegalArgumentException(String.format("Compensable %s of method %s, the retries should not below -1.", compensable, method.getName()));
		    }
		    loadMethodContext(method, compensationMethod);
		  }
		}
}
