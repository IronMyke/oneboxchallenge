package com.onebox.challenge;

import com.onebox.challenge.model.Cart;
import com.onebox.challenge.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Value( "${com.onebox.cartExpiryInSeconds}" )
	private int cartExpiryInSeconds;

	@Autowired
	private CartRepository cartRepository;

	@Scheduled(fixedRate = 10000)
	public void deleteOldCarts() {
		Date cutoff = new Date(System.currentTimeMillis() - cartExpiryInSeconds * 1000);
		List<Cart> carts = this.cartRepository.getAllUpdatedBefore(cutoff);
		this.cartRepository.deleteAll(carts);
	}
}
