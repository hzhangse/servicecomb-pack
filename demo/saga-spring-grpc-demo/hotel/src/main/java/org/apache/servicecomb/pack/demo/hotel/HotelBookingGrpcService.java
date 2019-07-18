package org.apache.servicecomb.pack.demo.hotel;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;
import org.apache.servicecomb.pack.omega.transport.grpc.GrpcServerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@Slf4j
@GrpcService(value = Hotel.class,interceptors= {GrpcServerInterceptor.class})
public class HotelBookingGrpcService extends HotelOrderGrpc.HotelOrderImplBase {
	@Autowired
	private HotelBookingService service;

	private final AtomicInteger id = new AtomicInteger(0);

	@Override
	
	public void order(Hotel.HotelRequest request, StreamObserver<Hotel.HotelReply> responseObserver) {
		HotelBooking booking = new HotelBooking();
		booking.setId(id.incrementAndGet());
		booking.setName(request.getName());
		booking.setAmount(request.getRooms());
		//service.order(booking);
		Hotel.HotelReply replyBuilder = service.order(booking);
		
		responseObserver.onNext(replyBuilder);
		responseObserver.onCompleted();
		log.info("hotel grpc Returning " + booking.toString());
	}
	

}