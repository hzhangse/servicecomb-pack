/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.pack.demo.hotel;

import org.apache.servicecomb.pack.omega.context.CallbackContext;
import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.transaction.MessageHandler;
import org.apache.servicecomb.pack.omega.transaction.SagaMessageSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TransactionAspectConfig {

	@Bean(name = { "iotcompensationContext" })
	CallbackContext compensationContext(OmegaContext omegaContext) {
		return new IotCallbackContext(omegaContext);
	}

	@Bean
	MessageHandler iotbullCompensationMessageHandler(SagaMessageSender sender,
			@Qualifier("iotcompensationContext") IotCallbackContext context, OmegaContext omegaContext) {
		return new IotbullCompensationMessageHandler(sender, context);
	}

	@Bean
	IotBeanProcessor iotbullCompensableAnnotationProcessor(OmegaContext omegaContext,
			@Qualifier("iotcompensationContext") CallbackContext compensationContext) {
		return new IotBeanProcessor(omegaContext, compensationContext);
	}
}
