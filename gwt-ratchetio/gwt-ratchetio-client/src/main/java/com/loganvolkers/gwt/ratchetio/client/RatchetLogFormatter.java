package com.loganvolkers.gwt.ratchetio.client;

import java.util.HashSet;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.impl.SerializableThrowable;

public class RatchetLogFormatter {

	public JavaScriptObject formatStackTrace(Throwable e) {

		JavaScriptObject trace = null;

		boolean first = true;
		if (e == null) {
			return null;
		}

		// For each cause, print the requested number of entries of its stack
		// trace, being careful to avoid getting stuck in an infinite loop.
		//
		Throwable currentCause = e;
		HashSet<Throwable> seenCauses = new HashSet<Throwable>();
		while (currentCause != null && !seenCauses.contains(currentCause)) {
			seenCauses.add(currentCause);

			final String className;
			if (currentCause instanceof SerializableThrowable.ThrowableWithClassName) {
				className = ((SerializableThrowable.ThrowableWithClassName) currentCause)
						.getExceptionClass();
			} else {
				className = currentCause.getClass().getName();
			}
			final String message = currentCause.getMessage();
			if (first) {
				trace = createTrace(className, message);
				first = false;
			} else {
				push(trace, "CausedBy:" + className + ":" + message, 0);
			}

			StackTraceElement[] stackElems = currentCause.getStackTrace();
			if (stackElems != null) {
				for (int i = 0; i < stackElems.length; ++i) {
					StackTraceElement element = stackElems[i];
					push(trace, element.getFileName(), element.getLineNumber());
				}
			}

			currentCause = currentCause.getCause();
		}
		fixOrder(trace);
		return trace;
	}
	private static void push(JavaScriptObject trace, String filename, int lineno){
		push0(trace, filename == null ? "null" : filename, lineno <= 0 ? 0 : lineno);
	}
	
	
	private static native void fixOrder(JavaScriptObject trace)/*-{
		trace.frames.reverse();
	}-*/;

	private static native void push0(JavaScriptObject trace, String filename, int lineno)/*-{
		trace.frames.push({'filename': filename, 'lineno': lineno});
	}-*/;

	public static native JavaScriptObject createTrace(String className, String message)/*-{
		return {exception: {'class': className, 'message': message}, frames: []}
	}-*/;

}
