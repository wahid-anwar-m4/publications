package com.metafour.jpa.publications.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class DataFeeder {
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		feedData();
	}
	
	private void feedData() {
		System.out.println("Feeder started!!!");
	}
}
