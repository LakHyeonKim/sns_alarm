package com.ssafy.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Alarm;

@Repository
public class AlarmDAOImpl implements AlarmDAO {
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public Alarm findLatestAlarm(long idMember) {
		return sqlsession.selectOne("AlarmMapper.findLatestAlarm",idMember);
	}
}
