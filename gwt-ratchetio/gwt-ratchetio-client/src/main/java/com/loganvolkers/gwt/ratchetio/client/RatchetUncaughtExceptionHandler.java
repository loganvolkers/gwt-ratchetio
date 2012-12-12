package com.loganvolkers.gwt.ratchetio.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.JavaScriptObject;

public class RatchetUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void onUncaughtException(Throwable e) {
		JavaScriptObject stackTrace = new RatchetLogFormatter().formatStackTrace(e);
		new RatchetJs().pushError(stackTrace);
	}

	public static void install(){
		GWT.setUncaughtExceptionHandler(new RatchetUncaughtExceptionHandler());
	}
}
