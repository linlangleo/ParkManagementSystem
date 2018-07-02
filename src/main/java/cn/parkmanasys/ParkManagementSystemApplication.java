package cn.parkmanasys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParkManagementSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ParkManagementSystemApplication.class, args);
	}
}
