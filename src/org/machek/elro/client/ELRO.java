package org.machek.elro.client;


import org.machek.elro.client.widgets.ConfigureDevicesTable;
import org.machek.elro.client.widgets.ShowDevicesTable;
import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ELRO implements EntryPoint {
	public static final PowerServiceAsync powerService = GWT.create(PowerService.class);
	
	private DeviceRegistry dsr = null;
	private Button reloadButton = new Button("Reload");
	private Button editConfiguration = new Button("Edit");
	private Button operations = new Button("Operate");
	private Button saveConfiguration = new Button("Save");
	private Button revertConfiguration = new Button("Revert");
	
	private ShowDevicesTable showDevicesTable = new ShowDevicesTable();
	private ConfigureDevicesTable configureDevicesTable = new ConfigureDevicesTable();
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		fetchConfiguredPowerDevices();
		
		reloadButton.addClickHandler(new ReloadConfigurationHandler());
		editConfiguration.addClickHandler(new EditConfigurationHandler());
		operations.addClickHandler(new ShowOperationsPanel());
		saveConfiguration.addClickHandler(new SaveConfigurationHandler());
		revertConfiguration.addClickHandler(new RevertConfigurationHandler());
		
		RootPanel.get("main").add(showDevicesTable);
		RootPanel.get("main").add(configureDevicesTable);
		
		
		RootPanel.get("reloadButton").add(reloadButton);
		RootPanel.get("reloadButton").add(editConfiguration);
		RootPanel.get("reloadButton").add(saveConfiguration);
		// RootPanel.get("reloadButton").add(revertConfiguration);
		RootPanel.get("reloadButton").add(operations);
		
	}
	
	
	private void fetchConfiguredPowerDevices() {
		powerService.getConfiguredDevices(
				new AsyncCallback<DeviceRegistry>() {
					public void onFailure(Throwable caught) { 
						caught.printStackTrace();
					}	
					public void onSuccess(DeviceRegistry result) {
						dsr = result;
						initOperateTable();
					}
				});
	}
	
	private void initOperateTable() {
		showDevicesTable.setDeviceRegistry(dsr);
		showDevicesTable.generateFlexTable();
		showDevicesTable.setVisible(true);
		configureDevicesTable.setVisible(false);
	}
	
	private void initConfigureTable() {
		configureDevicesTable.setDeviceRegistry(dsr);
		configureDevicesTable.generateFlexTable();
		configureDevicesTable.setVisible(true);
		showDevicesTable.setVisible(false);
	}
	
	private void switchToConfigureMode() {
		initConfigureTable();
	}
	
	private void switchToOperateMode() {
		initOperateTable();
	}
	
	private void saveConfiguration() {
		configureDevicesTable.saveChangesToDeviceRegistry();
		powerService.saveConfiguration(configureDevicesTable.getDeviceRegistry(), 
				new AsyncCallback() {
					public void onFailure(Throwable caught) { 
						caught.printStackTrace();
					}
					public void onSuccess(Object o) {
						fetchConfiguredPowerDevices();
					}
				});
	}
	
	private void revertConfiguration() {
		fetchConfiguredPowerDevices();
		configureDevicesTable.generateFlexTable();
	}
	
	private class ReloadConfigurationHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			fetchConfiguredPowerDevices();
		}
	}
	
	private class EditConfigurationHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			switchToConfigureMode();
		}
	}
	
	private class ShowOperationsPanel implements ClickHandler {
		public void onClick(ClickEvent event) {
			switchToOperateMode();
		}
	}
	
	private class SaveConfigurationHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			saveConfiguration();
		}
	}

	private class RevertConfigurationHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			revertConfiguration();
		}
	}
}

