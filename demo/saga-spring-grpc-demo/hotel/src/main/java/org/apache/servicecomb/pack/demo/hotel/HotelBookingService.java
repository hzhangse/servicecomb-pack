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

import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
class HotelBookingService {
	private Map<Integer, HotelBooking> bookings = new ConcurrentHashMap<>();
	private final AtomicInteger id = new AtomicInteger(0);
	// @Compensable(compensationMethod = "cancel" )
	// void order(HotelBooking booking) {
	//
	// if (booking.getAmount() > 2) {
	// throw new IllegalArgumentException("can not order the rooms large than two");
	// }
	// booking.confirm();
	// bookings.put(booking.getId(), booking);
	// }

	@Compensable(compensationMethod = "cancel")
	public Hotel.HotelReply order(HotelBooking request) {
		HotelBooking booking = new HotelBooking();
		booking.setId(id.incrementAndGet());
		booking.setName(request.getName());
		booking.setAmount(request.getAmount());
		// service.order(booking);
		
		final Hotel.HotelReply reply = Hotel.HotelReply.newBuilder().setAmount(booking.getAmount())
				.setId(booking.getId()).setName(booking.getName()).build();
		return reply;
	}

	void cancel(HotelBooking booking) {
		Integer id = booking.getId();
		// throw new IllegalArgumentException("hotel cancel exception");
		// if (bookings.containsKey(id)) {
		// bookings.get(id).cancel();
		// System.out.println("hotel booking cancel event:id="+id);
		// }
	}

	Collection<HotelBooking> getAllBookings() {
		return bookings.values();
	}

	void clearAllBookings() {
		bookings.clear();
	}
}
