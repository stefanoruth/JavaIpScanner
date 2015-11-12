import org.w3c.dom.Element;

public class DeviceStorage extends XmlStorage {

	@Override
	void save(String[][] data) {
		// Main Element
		Element rootElement = doc.createElement("devices");
		doc.appendChild(rootElement);
		// SetColumns
		String[] columns = {"status","ip","hostname","mac-address"};
		
		// Loop Data
		for(int i = 0; i < data.length; i++) {
			// Create Device
			Element device = doc.createElement("device");
			rootElement.appendChild(device);
			device.setAttribute("id", Integer.toString(i+1));
			
			for(int col = 0; col < data[i].length; col++) {
				// Set Elements
				Element elm = doc.createElement(columns[col]);
				elm.appendChild(doc.createTextNode(data[i][col]));
				device.appendChild(elm);
			}
		}
		
		sendToFile("C:\\Users\\stefa\\Documents\\file.xml");
		readFile("C:\\Users\\stefa\\Documents\\file.xml");
	}

}
