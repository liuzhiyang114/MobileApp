package com.smartlab.mobileapp.connection;

public class MobileClientApp {

	int _i;

	public void MobileClient(int i) {
		_i = i;
	}

	public String write(String msg) {
		String back = MobileClientService.WriteMsgToMSForCallBack("10.84.199.239",
				7777, msg);

//		System.out.println("client" + _i + " rev:" + back);
		return "client" + _i + " rev:" + back;
	}
}
