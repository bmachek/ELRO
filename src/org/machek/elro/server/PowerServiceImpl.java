package org.machek.elro.server;

import org.machek.elro.client.PowerService;
import org.machek.elro.shared.ConfigErrorException;
import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class PowerServiceImpl extends RemoteServiceServlet implements
		PowerService {

	
	public DeviceRegistry getConfiguredDevices() throws IllegalArgumentException {
		try {
			return DeviceConfigAdapter.parsePowerConfig();
		} catch (ConfigErrorException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void saveConfiguration(DeviceRegistry dr) {
		try {
			DeviceConfigAdapter.savePowerConfig(dr);
		} catch (ConfigErrorException e) {
			throw new IllegalArgumentException(e);
		}
		
		
	}
	
	public void switchOn(DeviceConfig dev) {
		try {
			DeviceSwitcher.switchOn(dev);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchOff(DeviceConfig dev) {
		try {
			DeviceSwitcher.switchOff(dev);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
