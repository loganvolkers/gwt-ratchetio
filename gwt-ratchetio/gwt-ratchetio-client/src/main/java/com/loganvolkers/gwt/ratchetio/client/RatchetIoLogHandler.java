package com.loganvolkers.gwt.ratchetio.client;

import com.google.gwt.user.client.Window;
import com.loganvolkers.gwt.ratchetio.shared.RatchetIoConstants;

public class RatchetIoLogHandler {

	
	public void doSomething(){
		Window.alert(RatchetIoConstants.errorMessage);
	}
}
