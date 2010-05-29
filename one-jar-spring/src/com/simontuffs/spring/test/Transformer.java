package com.simontuffs.spring.test;

import org.springframework.stereotype.Component;

@Component("transformer")
public class Transformer {

	public void run() {
		System.out.println("Transformer.run()");
	}
}
