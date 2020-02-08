package com.ssafy.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.ssafy.vo.Greeting;
import com.ssafy.vo.HelloMessage;

@Controller
public class GreetingController {
	
	@MessageMapping("/info")
	@SendToUser("/queue/info")
	public String alram(HelloMessage message) throws Exception {
		//Thread.sleep(1000);
		System.out.println("여기");
		return "바보";
	}
	
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
