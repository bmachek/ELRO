package org.machek.elro.client.widgets;

import java.util.Iterator;

import org.machek.elro.client.ELRO;
import org.machek.elro.shared.Constants;
import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

public class ShowDevicesTable extends AbstractDeviceTable {

	public ShowDevicesTable() {
		super();
	}

	public void generateFlexTable() {
		clear();
		
		Iterator<DeviceConfig> i = deviceRegistry.getDevices().iterator();
		int row = 0;
		
		while (i.hasNext()) {
			DeviceConfig dc = i.next();
			
			Button onButton = new Button("On");
			Button offButton = new Button("Off");
			
			onButton.addClickHandler(new PowerClickHandler(dc, Constants.RC_SWITCH_PI_ON));
			offButton.addClickHandler(new PowerClickHandler(dc, Constants.RC_SWITCH_PI_OFF));
			
			onButton.setStyleName("onButton");
			offButton.setStyleName("offButton");
			
			String img = "";
			
			if (dc.getDeviceType().equals(Constants.DEVICE_TYPE_RCSWITCH)) {
				img = Constants.IMG_WIFI;
			} else if (dc.getDeviceType().equals(Constants.DEVICE_TYPE_SISPMCTL)) {
				img = Constants.IMG_USB;
			}
			
			setWidget(row, 0, new HTML(img));
			setText(row, 1, dc.getDeviceName());
			setWidget(row, 2, onButton);
			setWidget(row, 3, offButton);
			
			row++;
		}
	}
	private class PowerClickHandler implements ClickHandler {

		private DeviceConfig dev;
		private String command;
		
		public PowerClickHandler(DeviceConfig dev, String onOrOff) {
			this.dev = dev;
			this.command = onOrOff;
		}
		
		public void onClick(ClickEvent event) {
			if (command.equals(Constants.RC_SWITCH_PI_OFF)) { 
				ELRO.powerService.switchOff(dev, new AsyncCallback() {
					public void onFailure(Throwable caught) {
						//TODO
					}
					public void onSuccess(Object result) {
						//TODO
					}
				});
			} else if (command.equals(Constants.RC_SWITCH_PI_ON)) {
				ELRO.powerService.switchOn(dev, new AsyncCallback() {
					public void onFailure(Throwable caught) {
						//TODO
					}
					public void onSuccess(Object result) {
						//TODO
					}
				});
			}
			
		}
		
	}

}

