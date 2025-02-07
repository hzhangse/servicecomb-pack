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

package org.apache.servicecomb.pack.alpha.server;

import static org.apache.servicecomb.pack.alpha.core.TaskStatus.DONE;
import static org.apache.servicecomb.pack.alpha.core.TaskStatus.NEW;
import static org.apache.servicecomb.pack.alpha.core.TaskStatus.PENDING;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.servicecomb.pack.alpha.core.Command;
import org.apache.servicecomb.pack.alpha.core.CommandRepository;
import org.apache.servicecomb.pack.alpha.core.TxEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kamon.annotation.EnableKamon;
import kamon.annotation.Segment;

@EnableKamon
public class SpringCommandRepository implements CommandRepository {
  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  private final TxEventEnvelopeRepository eventRepository;
  private final CommandEntityRepository commandRepository;

  SpringCommandRepository(TxEventEnvelopeRepository eventRepository, CommandEntityRepository commandRepository) {
    this.eventRepository = eventRepository;
    this.commandRepository = commandRepository;
  }

  @Override
  @Segment(name = "saveCompensationCommands", category = "application", library = "kamon")
  public void saveCompensationCommands(String globalTxId) {
    List<TxEvent> events = eventRepository
        .findStartedEventsWithMatchingEndedButNotCompensatedEvents(globalTxId);

    Map<String, Command> commands = new LinkedHashMap<>();

    for (TxEvent event : events) {
      commands.computeIfAbsent(event.localTxId(), k -> new Command(event));
    }

    for (Command command : commands.values()) {
      LOG.info("Saving compensation command {}", command);
      try {
        commandRepository.save(command);
      } catch (Exception e) {
        LOG.warn("Failed to save some command {}", command);
      }
      LOG.info("Saved compensation command {}", command);
    }
  }

  @Override
  @Segment(name = "markCommandAsDone", category = "application", library = "kamon")
  public void markCommandAsDone(String globalTxId, String localTxId) {
    commandRepository.updateStatusByGlobalTxIdAndLocalTxId(DONE.name(), globalTxId, localTxId);
  }

  @Override
  @Segment(name = "findUncompletedCommands", category = "application", library = "kamon")
  public List<Command> findUncompletedCommands(String globalTxId) {
    return commandRepository.findByGlobalTxIdAndStatus(globalTxId, NEW.name());
  }

  @Transactional
  @Override
  @Segment(name = "findFirstCommandToCompensate", category = "application", library = "kamon")
  public List<Command> findFirstCommandToCompensate() {
    List<Command> commands = commandRepository
        .findFirstGroupByGlobalTxIdWithoutPendingOrderByIdDesc();

    commands.forEach(command ->
        commandRepository.updateStatusByGlobalTxIdAndLocalTxId(
            NEW.name(),
            PENDING.name(),
            command.globalTxId(),
            command.localTxId()));

		return commands;
	}

	

	@Override
	@Segment(name = "updateCommand", category = "application", library = "kamon")
	public Command updateCommand(Command command) {
		 Command result = commandRepository.save(command);
		return result;	
		
	}

	

	@Override
	@Transactional
	@Segment(name = "findPendingServiceCommandsOrderById", category = "application", library = "kamon")
	public List<Command> findPendingServiceCommandsOrderById(int pageindex,int pagesize) {
		PageRequest TEN_CMD_REQUEST = new PageRequest(pageindex, pagesize);
		Page<Command> page = commandRepository.findPendingServiceCommandsOrderById(TEN_CMD_REQUEST);
		
		return page.getContent();
	}
	
	
	@Override
	@Segment(name = "findPendingServiceCommandsCount", category = "application", library = "kamon")
	public Integer findPendingServiceCommandsCount() {
		return commandRepository.findPendingServiceCommandsCount();
	}
}
