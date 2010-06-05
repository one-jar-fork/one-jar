package com.simontuffs.onejar.spring.main;

import org.springframework.stereotype.Component;

@Component("transformer")
public class Transformer {

	public void run() {
		System.out.println("Transformer.run()");
	}
}
