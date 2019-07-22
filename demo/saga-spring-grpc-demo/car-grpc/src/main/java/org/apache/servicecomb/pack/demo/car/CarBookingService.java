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

package org.apache.servicecomb.pack.demo.car;

import org.apache.servicecomb.pack.demo.hotel.Hotel;
import org.apache.servicecomb.pack.demo.hotel.HotelOrderGrpc;
import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;
import org.apache.servicecomb.pack.omega.transport.grpc.GrpcClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class CarBookingService {
	private Map<Integer, CarBooking> bookings = new ConcurrentHashMap<>();

	@Value("${hotel.service.address:http://localhost:8081}")
	private String hotelServiceUrl;

	@Autowired
	private RestTemplate template;

	@GrpcClient(value = "hotel-grpc-server", interceptors = { GrpcClientInterceptor.class })
	private Channel serverChannel;

	public Hotel.HotelReply orderHotelByGrpc(String name, Integer rooms) {
		HotelOrderGrpc.HotelOrderBlockingStub stub = HotelOrderGrpc.newBlockingStub(serverChannel);
		Hotel.HotelReply response = stub.order(Hotel.HotelRequest.newBuilder().setName(name).setRooms(rooms).build());
		return response;
	}

	@Compensable(compensationMethod = "cancel")
	public Car.CarReply.Builder order1(CarBooking request) {
		
		orderHotelByGrpc(request.getName(),request.getAmount());
		final Car.CarReply.Builder replyBuilder = Car.CarReply.newBuilder()
				.setAmount(request.getAmount()).setId(request.getId()).setName(request.getName());
		int i= 1/0;
		return replyBuilder;
		
	}
//	@Compensable(compensationMethod = "cancel")
//	void order(CarBooking booking) {
//		int rooms = booking.getAmount() - 1;
//
//		template.postForEntity(hotelServiceUrl + "/order/{name}/{rooms}", null, String.class, "test", rooms);
//
//		if (booking.getAmount() > 2) {
//			throw new IllegalArgumentException("can not order the cars large than two");
//		}
//		booking.confirm();
//		bookings.put(booking.getId(), booking);
//
//	}

	void cancel(CarBooking booking) {
		Integer id = booking.getId();
		if (bookings.containsKey(id)) {
			bookings.get(id).cancel();
			System.out.println("car booking cancel event:id=" + id);
		}
		// Just sleep a while to ensure the Compensated event is after ordering TxAbort
		// event
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Just ignore the exception
		}
	}

	Collection<CarBooking> getAllBookings() {
		return bookings.values();
	}

	void clearAllBookings() {
		bookings.clear();
	}
}
