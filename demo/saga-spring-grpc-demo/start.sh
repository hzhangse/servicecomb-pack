java -Dspring.profiles.active=mysql -Dloader.path=./plugins -D"spring.datasource.url=jdbc:mysql://192.168.10.128:3306/saga?serverTimezone=GMT%2b8&useSSL=false"  -D"spring.datasource.username=root" -D"spring.datasource.password=root" -jar alpha-server-0.5.0-SNAPSHOT-exec.jar



java -Dserver.port=8081 -Dalpha.cluster.address=localhost:8080 -jar hotel-0.5.0-SNAPSHOT-exec.jar

java -Dserver.port=8082 -Dalpha.cluster.address=localhost:8080 -jar car-0.5.0-SNAPSHOT-exec.jar

java -Dserver.port=8083 -Dalpha.cluster.address=localhost:8080 -Dcar.service.address=http://localhost:8082 -Dhotel.service.address=http://localhost:8081  -jar booking-0.5.0-SNAPSHOT-exec.jar


 curl -X POST http://localhost:8083/booking/test/2/2

 curl -X POST http://localhost:8083/booking/test/2/2

curl http://localhost:8081/bookings
