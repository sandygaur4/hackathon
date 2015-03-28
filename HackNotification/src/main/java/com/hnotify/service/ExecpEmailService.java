package com.hnotify.service;

public interface ExecpEmailService {

	void printAndSendExecption(String className, Exception e, String type);

	void sendMailOfAddSeat(String type,String strException, String cinemacode, long sessionid, long userid);

}
