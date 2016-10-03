package org.machek.elro.client.widgets;

import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.user.client.ui.FlexTable;


public abstract class AbstractDeviceTable extends FlexTable {
	
	DeviceRegistry deviceRegistry;
	
	public AbstractDeviceTable() { }
	
	public abstract void generateFlexTable();

	public DeviceRegistry getDeviceRegistry() {
		return deviceRegistry;
	}

	public void setDeviceRegistry(DeviceRegistry deviceRegistry) {
		this.deviceRegistry = deviceRegistry;
	}
	
}
