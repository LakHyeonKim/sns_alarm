package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.ssafy.service.AlarmService;
import com.ssafy.vo.Alarm;
import com.ssafy.vo.Greeting;
import com.ssafy.vo.HelloMessage;

@Controller
public class GreetingController {

	@Autowired
	private AlarmService alarmService;

	@MessageMapping("/info")
	@SendToUser("/queue/info")
	public Alarm alram(HelloMessage message) throws Exception {
		long idMember = Long.parseLong(message.getMemberId());
		Alarm alarm = alarmService.findLatestAlarm(idMember);
		return alarm;
	}

//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public Greeting greeting(HelloMessage message) throws Exception {
//		Thread.sleep(1000); // simulated delay
//		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//	}
}
