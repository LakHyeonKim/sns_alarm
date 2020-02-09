package com.ssafy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.AlarmDAO;
import com.ssafy.vo.Alarm;

@Service
public class AlarmSerivceImpl implements AlarmService {

	@Autowired
	private AlarmDAO alarmdao;
	
	@Override
	public Alarm findLatestAlarm(long idMember) {
		return alarmdao.findLatestAlarm(idMember);
	}

}
