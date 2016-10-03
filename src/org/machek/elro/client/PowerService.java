package org.machek.elro.client;

import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("power")
public interface PowerService extends RemoteService {
	DeviceRegistry getConfiguredDevices() throws IllegalArgumentException;
	void saveConfiguration (DeviceRegistry dr) throws IllegalArgumentException;
	void switchOn(DeviceConfig dev) throws IllegalArgumentException;
	void switchOff(DeviceConfig dev) throws IllegalArgumentException;
}
