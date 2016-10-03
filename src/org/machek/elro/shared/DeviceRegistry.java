package org.machek.elro.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeviceRegistry implements Serializable {

	private List<DeviceConfig> devices;

	public DeviceRegistry() {
		devices = new ArrayList<DeviceConfig>();
	}
	
	public List<DeviceConfig> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceConfig> devices) {
		this.devices = devices;
	}
	
	public String toString() {
		String res = "";
		Iterator<DeviceConfig> i = devices.iterator();
		while (i.hasNext()) {
			res += " " + i.next().toString();
		}
		
		return res;
	}
	
}
