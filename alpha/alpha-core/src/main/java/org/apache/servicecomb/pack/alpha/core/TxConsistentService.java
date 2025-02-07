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

package org.apache.servicecomb.pack.alpha.core;

import static org.apache.servicecomb.pack.common.EventType.SagaEndedEvent;
import static org.apache.servicecomb.pack.common.EventType.TxAbortedEvent;
import static org.apache.servicecomb.pack.common.EventType.TxStartedEvent;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;

import org.apache.servicecomb.pack.common.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kamon.annotation.EnableKamon;
import kamon.annotation.Segment;

@EnableKamon
public class TxConsistentService {
  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final TxEventRepository eventRepository;

  private final List<String> types = Arrays.asList(TxStartedEvent.name(), SagaEndedEvent.name());

  public TxConsistentService(TxEventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }
  @Segment(name = "handleTxEvent", category = "application", library = "kamon")
  public boolean handle(TxEvent event) {
    if (types.contains(event.type()) && isGlobalTxAborted(event)) {
      LOG.info("Transaction event {} rejected, because its parent with globalTxId {} was already aborted",
          event.type(), event.globalTxId());
      return false;
    }
    	//resend back when compensate failed
		if (event.type().equalsIgnoreCase(EventType.TxCompensatedEvent.name())) {
			//commandRepository.updateByGlobalTxIdAndLocalTxId(TaskStatus.PENDING.name(),event.payloads(),event.globalTxId(),event.localTxId());
//			((PushBackOmegaCallback) omegaCallback).offerCompensateEvent(event);
			//补偿事件入库幂等性判断，目前基于数据库判断，后续改进基于本地缓存过滤
			int size = eventRepository.findDuplicateCompensatedEvent(event.localTxId(), event.globalTxId(),event.parentTxId()).size();
			if (size <1) eventRepository.save(event);
		}else{ 
			eventRepository.save(event);
	    }
    return true;
  }

  @Segment(name = "isGlobalTxAborted", category = "application", library = "kamon")
  private boolean isGlobalTxAborted(TxEvent event) {
    return !eventRepository.findTransactions(event.globalTxId(), TxAbortedEvent.name()).isEmpty();
  }
}
