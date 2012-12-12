package com.loganvolkers.gwt.ratchetio.client;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class RatchetIoLogHandler extends Handler{

	private final RatchetJs ratchetJs;

	public RatchetIoLogHandler(RatchetJs ratchetJs){
		this.ratchetJs = ratchetJs;
	}
	
	@Override
	public void publish(LogRecord record) {
		ratchetJs.sendError("");
	}

	@Override
	public void flush() {
		// Intentional no-op
	}

	@Override
	public void close() throws SecurityException {
		// Intentional no-op
	}
}
