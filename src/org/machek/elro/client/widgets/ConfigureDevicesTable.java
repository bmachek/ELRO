package org.machek.elro.client.widgets;

import java.util.ArrayList;
import java.util.Iterator;

import org.machek.elro.shared.Constants;
import org.machek.elro.shared.DeviceConfig;
import org.machek.elro.shared.DeviceRegistry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class ConfigureDevicesTable extends AbstractDeviceTable {

	public ConfigureDevicesTable() {
		super();
	}

	public void generateFlexTable() {
		clear();
		
		generateHeaders();
		
		Iterator<DeviceConfig> i = deviceRegistry.getDevices().iterator();
		int row = 1;
		
		while (i.hasNext()) {
			int col = 0;
			
			DeviceConfig dc = i.next();

			Button deleteButton = new Button("X");
			deleteButton.setStyleName("deleteButton");
			deleteButton.addClickHandler(new DeleteHandler());
			
			ListBox deviceType = new ListBox();
			deviceType.addItem(Constants.DEVICE_TYPE_SISPMCTL);
			deviceType.addItem(Constants.DEVICE_TYPE_RCSWITCH);
			
			if (dc.getDeviceType().equals(Constants.DEVICE_TYPE_SISPMCTL)) {
				deviceType.setSelectedIndex(0);
			} else if (dc.getDeviceType().equals(Constants.DEVICE_TYPE_RCSWITCH)) {
				deviceType.setSelectedIndex(1);
			}
			
			TextBox deviceName = new TextBox();
			deviceName.setText(dc.getDeviceName());
			
			TextBox systemCode = new TextBox();
			systemCode.setText(dc.getSystemCode());
			systemCode.setEnabled(dc.getDeviceType().equals(Constants.DEVICE_TYPE_RCSWITCH));
			
			TextBox deviceCode = new TextBox();
			deviceCode.setText(dc.getDeviceCode());

			
			
			setWidget(row, col++, deviceType);
			setWidget(row, col++, deviceName);
			setWidget(row, col++, systemCode);
			setWidget(row, col++, deviceCode);
			setWidget(row, col++, deleteButton);
			
			row++;
		}
		
		generateFooter();
	}	
	
	private void generateHeaders() {
		int col = 0;
		setText(0, col++, "Device type");
		setText(0, col++, "Device name");
		setText(0, col++, "System Code");
		setText(0, col++, "Device Code");
		setText(0, col++, "");
	}
	
	private void generateFooter() {
		
		int row = deviceRegistry.getDevices().size() + 1;

		/*
		Button saveButton = new Button("Save");
		saveButton.addClickHandler(new SaveHandler());
		*/
		
		Button addButton = new Button("+");
		addButton.setStyleName("addButton");
		addButton.addClickHandler(new AddHandler());
		
		// setWidget(row, 0, saveButton);
		setWidget(row, 4, addButton);
	}
	
	private void addEmptyRow() {
		int row = getRowCount() - 1;
		int col = 0;
		
		insertRow(row);
		
		Button deleteButton = new Button("X");
		deleteButton.setStyleName("deleteButton");
		deleteButton.addClickHandler(new DeleteHandler());
		
		ListBox deviceType = new ListBox();
		deviceType.addItem(Constants.DEVICE_TYPE_SISPMCTL);
		deviceType.addItem(Constants.DEVICE_TYPE_RCSWITCH);
		
		TextBox deviceName = new TextBox();
		
		TextBox systemCode = new TextBox();
		
		TextBox deviceCode = new TextBox();
		
		setWidget(row, col++, deviceType);
		setWidget(row, col++, deviceName);
		setWidget(row, col++, systemCode);
		setWidget(row, col++, deviceCode);
		setWidget(row, col++, deleteButton);
	}
	
	public void saveChangesToDeviceRegistry() {
		deviceRegistry = new DeviceRegistry();
		
		ArrayList<DeviceConfig> devices = new ArrayList<DeviceConfig>();
		
		int startRow = 1;
		int endRow = getRowCount() - 1;
		
		for (int i = startRow ; i < endRow; i++) {
			DeviceConfig dc = new DeviceConfig();

			ListBox deviceType = (ListBox)getWidget(i, 0);
			
			dc.setDeviceType(deviceType.getValue(deviceType.getSelectedIndex()));
			dc.setDeviceName(((TextBox)getWidget(i, 1)).getText());
			dc.setSystemCode(((TextBox)getWidget(i, 2)).getText());
			dc.setDeviceCode(((TextBox)getWidget(i, 3)).getText());
			
			devices.add(dc);
		}
		
		deviceRegistry.setDevices(devices);
	}
	
	
	private class DeleteHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			int row = getCellForEvent(event).getRowIndex();
			removeRow(row);
		}
	}
	
	private class AddHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			addEmptyRow();
		}
	}
	
}