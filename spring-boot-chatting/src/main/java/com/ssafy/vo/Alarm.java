package com.ssafy.vo;

public class Alarm {
	long idAlarm;
	long memberCaller;
	long memberReceiver;
	long postId;
	long likeId;
	long followId;
	long isRead;
	long access;
	
	public Alarm() {
		super();
	}

	public Alarm(long idAlarm, long memberColler, long memberReceiver, long postId, long likeId, long followId,
			long isRead, long access) {
		super();
		this.idAlarm = idAlarm;
		this.memberCaller = memberColler;
		this.memberReceiver = memberReceiver;
		this.postId = postId;
		this.likeId = likeId;
		this.followId = followId;
		this.isRead = isRead;
		this.access = access;
	}

	public long getIdAlarm() {
		return idAlarm;
	}

	public void setIdAlarm(long idAlarm) {
		this.idAlarm = idAlarm;
	}

	public long getMemberColler() {
		return memberCaller;
	}

	public void setMemberColler(long memberColler) {
		this.memberCaller = memberColler;
	}

	public long getMemberReceiver() {
		return memberReceiver;
	}

	public void setMemberReceiver(long memberReceiver) {
		this.memberReceiver = memberReceiver;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getLikeId() {
		return likeId;
	}

	public void setLikeId(long likeId) {
		this.likeId = likeId;
	}

	public long getFollowId() {
		return followId;
	}

	public void setFollowId(long followId) {
		this.followId = followId;
	}

	public long getIsRead() {
		return isRead;
	}

	public void setIsRead(long isRead) {
		this.isRead = isRead;
	}

	public long getAccess() {
		return access;
	}

	public void setAccess(long access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "Alarm [idAlarm=" + idAlarm + ", memberColler=" + memberCaller + ", memberReceiver=" + memberReceiver
				+ ", postId=" + postId + ", likeId=" + likeId + ", followId=" + followId + ", isRead=" + isRead
				+ ", access=" + access + "]";
	}
}
