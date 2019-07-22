/**
 * 
 */
package org.apache.servicecomb.pack.demo.hotel;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.servicecomb.pack.omega.context.CallbackContext;
import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ryan
 *
 */
public class IotCallbackContext extends CallbackContext {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final Map<String, CallbackContextInternal> contexts = new ConcurrentHashMap<>();
	private final OmegaContext omegaContext;

	public IotCallbackContext(OmegaContext omegaContext) {
		 super(omegaContext);
		 this.omegaContext = omegaContext;		
	}

	public void addCallbackContext(String key, Method compensationMethod, Object target) {
		compensationMethod.setAccessible(true);
		contexts.put(key, new CallbackContextInternal(target, compensationMethod));
	}

	public void applyCallback(String globalTxId, String localTxId, String callbackMethod, Object... payloads) throws Throwable {
		CallbackContextInternal contextInternal = contexts.get(callbackMethod);
		String oldGlobalTxId = omegaContext.globalTxId();
		String oldLocalTxId = omegaContext.localTxId();
		try {
			omegaContext.setGlobalTxId(globalTxId);
			omegaContext.setLocalTxId(localTxId);
			contextInternal.callbackMethod.invoke(contextInternal.target, payloads);
			LOG.info("Callback transaction with global tx id [{}], local tx id [{}]", globalTxId, localTxId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Pre-checking for callback method " + contextInternal.callbackMethod.toString()
					+ " was somehow skipped, did you forget to configure callback method checking on service startup?:{}",
					e.getCause().getMessage());
			throw e;
		} finally {
			omegaContext.setGlobalTxId(oldGlobalTxId);
			omegaContext.setLocalTxId(oldLocalTxId);
		}
	}

	private static final class CallbackContextInternal {
		private final Object target;

		private final Method callbackMethod;

		private CallbackContextInternal(Object target, Method callbackMethod) {
			this.target = target;
			this.callbackMethod = callbackMethod;
		}
	}
}
