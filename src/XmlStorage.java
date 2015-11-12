import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public abstract class XmlStorage {
	protected Document doc;
	
	// Constructor
	public XmlStorage(){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	// Build Xml
	abstract void save(String[][] data);
	
	// Store
	protected void sendToFile(String path)
	{
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(this.doc);
			StreamResult result = new StreamResult(new File(path));
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Data has been saved to: "+path);
	}
	
	// Read A File To Console
	public void readFile(String path)
	{
		try {
			String line;
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
	        }   

	            // Always close files.
	            bufferedReader.close(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}