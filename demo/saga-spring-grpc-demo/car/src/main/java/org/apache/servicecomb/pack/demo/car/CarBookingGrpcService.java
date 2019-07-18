package org.apache.servicecomb.pack.demo.car;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.servicecomb.pack.omega.transport.grpc.GrpcServerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@Slf4j
@GrpcService(value = Car.class, interceptors = { GrpcServerInterceptor.class })
public class CarBookingGrpcService extends CarOrderGrpc.CarOrderImplBase {
	private final AtomicInteger id = new AtomicInteger(0);
	private Map<Integer, CarBooking> bookings = new ConcurrentHashMap<>();

	@Autowired
	private CarBookingService service;

	@Override
	public void order(Car.CarRequest request, StreamObserver<Car.CarReply> responseObserver) {
		CarBooking booking = new CarBooking();
		booking.setId(id.incrementAndGet());
		booking.setName(request.getName());
		booking.setAmount(request.getCars());

		responseObserver.onNext(service.order1(booking).build());
		responseObserver.onCompleted();
		log.info("car grpc Returning " + booking.toString());
	}

}