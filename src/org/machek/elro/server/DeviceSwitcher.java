package org.machek.elro.server;

import java.io.IOException;

import org.machek.elro.shared.Constants;
import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.Util;

public class DeviceSwitcher {

	public static void switchOn(DeviceConfig dev) throws Exception {
		if (dev.getDeviceType().equals(Constants.DEVICE_TYPE_RCSWITCH)) {
			rcswitchPiSwitchOn(dev);
		} else if (dev.getDeviceType().equals(Constants.DEVICE_TYPE_SISPMCTL)) {
			sispmctlSwitchOn(dev);
		}
	}

	public static void switchOff(DeviceConfig dev) throws Exception {
		if (dev.getDeviceType().equals(Constants.DEVICE_TYPE_RCSWITCH)) {
			rcswitchPiSwitchOff(dev);
		} else if (dev.getDeviceType().equals(Constants.DEVICE_TYPE_SISPMCTL)) {
			sispmctlSwitchOff(dev);
		}
	}

	private static void rcswitchPiSwitchOff(DeviceConfig dev)
			throws IOException {
		String[] cmd = { Constants.SUDO_COMMAND, Constants.RC_SWITCH_PI_EXECUTABLE, dev.getSystemCode(), dev.getDeviceCode(), Constants.RC_SWITCH_PI_OFF };
		System.out.println(Util.concatStringArray(cmd));
		Runtime.getRuntime().exec(cmd);
	}
	
	private static void rcswitchPiSwitchOn(DeviceConfig dev) throws IOException {
		String[] cmd = { Constants.SUDO_COMMAND, Constants.RC_SWITCH_PI_EXECUTABLE, dev.getSystemCode(), dev.getDeviceCode(), Constants.RC_SWITCH_PI_ON };
		System.out.println(Util.concatStringArray(cmd));
		Runtime.getRuntime().exec(cmd);
	}
	
	private static void sispmctlSwitchOn(DeviceConfig dev)
			throws IOException {
		
		String[] cmd = { Constants.SUDO_COMMAND, Constants.SISPMCTL_EXECUTABLE, Constants.SISPMCTL_ON, dev.getDeviceCode() };
		System.out.println(Util.concatStringArray(cmd));
		Runtime.getRuntime().exec(cmd);
		
	}

	private static void sispmctlSwitchOff(DeviceConfig dev)
			throws IOException {
		
		String[] cmd = { Constants.SUDO_COMMAND, Constants.SISPMCTL_EXECUTABLE, Constants.SISPMCTL_OFF, dev.getDeviceCode() };
		System.out.println(Util.concatStringArray(cmd));
		Runtime.getRuntime().exec(cmd);
		
	}

	
}
