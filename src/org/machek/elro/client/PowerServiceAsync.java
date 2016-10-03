package org.machek.elro.client;

import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface PowerServiceAsync {
	void getConfiguredDevices(AsyncCallback<DeviceRegistry> callback) throws IllegalArgumentException;
	void saveConfiguration(DeviceRegistry dr, AsyncCallback cb) throws IllegalArgumentException;
	void switchOn(DeviceConfig dev, AsyncCallback callback) throws IllegalArgumentException;
	void switchOff(DeviceConfig dev, AsyncCallback callback) throws IllegalArgumentException;
}
