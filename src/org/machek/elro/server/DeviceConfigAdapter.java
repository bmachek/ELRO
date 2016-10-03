package org.machek.elro.server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.machek.elro.shared.ConfigErrorException;
import org.machek.elro.shared.Constants;
import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.DeviceRegistry;


public class DeviceConfigAdapter {

	private static final String DEVICE_CODE = ".deviceCode";
	private static final String SYSTEM_CODE = ".systemCode";
	private static final String USED_POWER_SWITCHES = "usedPowerSwitches";
	private static final String DEVICE_TYPE = ".deviceType";

	public static DeviceRegistry parsePowerConfig() throws ConfigErrorException {
		Properties props = new Properties();
		
		DeviceRegistry psr = new DeviceRegistry();
		
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(Constants.POWER_CONFIG_PROPERTIES));
			
			props.load(stream);
			stream.close();
			
			String[] usedDevicesStrings = props.getProperty(USED_POWER_SWITCHES).split(",");
			
			Constants.SISPMCTL_EXECUTABLE = props.getProperty(Constants.DEVICE_TYPE_SISPMCTL);
			Constants.RC_SWITCH_PI_EXECUTABLE = props.getProperty(Constants.DEVICE_TYPE_RCSWITCH);
			
			
			for (int i=0; i < usedDevicesStrings.length; i++) {
				usedDevicesStrings[i] = usedDevicesStrings[i].trim();
				
				DeviceConfig dc = new DeviceConfig();
				
				dc.setDeviceName(usedDevicesStrings[i]);
				dc.setSystemCode(props.getProperty(usedDevicesStrings[i] + SYSTEM_CODE));
				dc.setDeviceCode(props.getProperty(usedDevicesStrings[i] + DEVICE_CODE));
				dc.setDeviceType(props.getProperty(usedDevicesStrings[i] + DEVICE_TYPE));
			
				psr.getDevices().add(dc);
				
			}
			
			return psr;
		} catch (FileNotFoundException fe) {
				props = new Properties();
				return new DeviceRegistry();
		} catch (IOException e) {
			throw new ConfigErrorException(e);
		} 
	}

	public static void savePowerConfig(DeviceRegistry dr) throws ConfigErrorException {

		System.out.println(dr);
		
		Properties p = new Properties();
		
		StringBuffer usedDevices = new StringBuffer();
		
		Iterator<DeviceConfig> i = dr.getDevices().iterator();
		
		while (i.hasNext()) {
			DeviceConfig dc = i.next();
			p.setProperty(dc.getDeviceName() + SYSTEM_CODE, dc.getSystemCode());
			p.setProperty(dc.getDeviceName() + DEVICE_CODE, dc.getDeviceCode());
			p.setProperty(dc.getDeviceName() + DEVICE_TYPE, dc.getDeviceType());
			usedDevices.append(", ");
			usedDevices.append(dc.getDeviceName());
		}
		
		usedDevices.delete(0, 1);
		p.setProperty(USED_POWER_SWITCHES, usedDevices.toString());
		
		p.setProperty(Constants.DEVICE_TYPE_SISPMCTL, Constants.SISPMCTL_EXECUTABLE);
		p.setProperty(Constants.DEVICE_TYPE_RCSWITCH, Constants.RC_SWITCH_PI_EXECUTABLE);
		
		try {
			p.store(new FileWriter((Constants.POWER_CONFIG_PROPERTIES)), "Device configuration");
		} catch (IOException e) {
			throw new ConfigErrorException(e);
		}
		
	}
}
