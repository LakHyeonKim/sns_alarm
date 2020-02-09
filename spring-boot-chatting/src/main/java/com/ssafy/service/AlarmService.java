package com.ssafy.service;

import com.ssafy.vo.Alarm;

public interface AlarmService {
	public Alarm findLatestAlarm(long idMember);
}
