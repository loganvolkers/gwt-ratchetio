package com.loganvolkers.demo.ratchetio.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.loganvolkers.gwt.ratchetio.client.RatchetJs;
import com.loganvolkers.gwt.ratchetio.client.RatchetUncaughtExceptionHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DemoApp implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	RatchetJs.install("475251d1b23e4706b58d87d31159fd09");
	RatchetUncaughtExceptionHandler.install();

    final Button sendButton = new Button("Simulate exception");
    final TextBox nameField = new TextBox();
    final Label errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();


    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler{
    /* Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
    	  throw new RuntimeException("Something bad happened");
      }

    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
  }
}
