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

import java.util.concurrent.ConcurrentHashMap;

import org.apache.servicecomb.pack.omega.transaction.MessageHandler;
import org.apache.servicecomb.pack.omega.transaction.SagaMessageSender;
import org.apache.servicecomb.pack.omega.transaction.TxCompensatedEvent;

public class IotbullCompensationMessageHandler implements MessageHandler {
	private SagaMessageSender sender;

	private IotCallbackContext context;

	private  ConcurrentHashMap<String,String> requestMap= new ConcurrentHashMap<>(8);

	public IotbullCompensationMessageHandler(SagaMessageSender sender, IotCallbackContext context) {
		this.sender = sender;
		this.context = context;
	}

	@Override
	public void onReceive(String globalTxId, String localTxId, String parentTxId, String compensationMethod,
			Object... payloads) {
		String key = globalTxId+localTxId;
		try {			
			if (!requestMap.contains(key)) {
				requestMap.put(key, key);
			    context.applyCallback(globalTxId, localTxId, compensationMethod, payloads);
			    sender.send(new TxCompensatedEvent(globalTxId, localTxId, parentTxId, compensationMethod));			   
			}
		} catch (Throwable e) {			 
			//if (retries-- > 0)			{
			//	TxEvent event = new TxEvent(EventType.TxCompensatedEvent, globalTxId, localTxId, parentTxId, compensationMethod, 0, "", retries,e.getCause().getMessage());
			//	sender.send(event);}
			e.printStackTrace();
		}finally {
			requestMap.remove(key);
		}

	}

}
