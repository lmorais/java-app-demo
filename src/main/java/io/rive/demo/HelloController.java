package io.rive.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

	@Value("${hello.name}")
	private String name;

	@RequestMapping("/hello")
	public String sayHello() {
		return String.format("%s %s %s",  "Hello ", name, ", you are welcome!");		
	}

}