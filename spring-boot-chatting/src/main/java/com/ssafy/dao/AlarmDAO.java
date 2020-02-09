package com.ssafy.dao;

import com.ssafy.vo.Alarm;

public interface AlarmDAO {
	public Alarm findLatestAlarm(long idMember);
}
