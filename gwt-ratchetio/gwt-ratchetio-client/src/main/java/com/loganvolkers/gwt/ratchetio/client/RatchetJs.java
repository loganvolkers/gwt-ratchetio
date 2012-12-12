package com.loganvolkers.gwt.ratchetio.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;

/**
 * The Ratchet Javascript library
 * 
 * @author lvolkers
 * 
 */
public class RatchetJs {

	public static void install(String clientSideToken) {
		init(clientSideToken);
		inject();
	}

	public void sendError(String error) {
		sendError0(error);
	}

	public void pushError(JavaScriptObject trace) {
		pushRaw0(trace);
	}

	private native void pushRaw0(JavaScriptObject rawObj)/*-{
															$wnd._ratchet.items.push({body: {trace: rawObj}});
															$wnd._ratchet.handleEvents();
															}-*/;

	private native void sendError0(String errorMsg)/*-{
													$wnd._ratchet.push({level: 'warning', msg: errorMsg, point: {x: 5, y: 10}});
													}-*/;

	static void inject() {
		ScriptInjector
				.fromUrl("//d2tf6sbdgil6xr.cloudfront.net/js/1/ratchet.min.js")
				.setWindow(ScriptInjector.TOP_WINDOW)
				.setCallback(new Callback<Void, Exception>() {
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onFailure(Exception reason) {
						// TODO Auto-generated method stub
					}
				}).inject();
	}

	static native void init(String clientSideToken)/*-{
													// JSNI method
													$wnd._ratchetParams = {"server.environment": "production"};
													$wnd._ratchetParams["notifier.snippet_version"] = "1";
													$wnd._ratchet=[clientSideToken, $wnd._ratchetParams];
													$wnd.onerror=function(e,u,l){$wnd._ratchet.push({_t:'uncaught',e:e,u:u,l:l});};
													}-*/;
}