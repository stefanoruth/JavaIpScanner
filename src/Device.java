import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {
	public String name = "";
	public String ip = "";
	public String mac = "";
	public Boolean online = false;
	
	public Device(String ip) {
		this.ip = ip;
	}
	
	public String status()
	{
		return this.online ? "Online" : "Offline";
	}
	
	
	public String info()
	{
		String data = this.status() +" - "+ this.ip;
		
		if(this.name != null)
			data += " - "+this.name;
		
		if(this.mac != null)
			data += " - "+this.mac;
			
		return data;
	}
	
	public boolean ping()
	{
		try {
			Process p = Runtime.getRuntime().exec("ping -n 1 "+this.ip);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String s = "";
			while ((s = inputStream.readLine()) != null) {
				if(s.toLowerCase().contains("packets:") && s.toLowerCase().contains("received = 1")){
					this.online = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.online;
	}
	
	public void getDeviceInfo()
	{
		try {
			if(!this.ping()) {
				return;
			}
			Process p = Runtime.getRuntime().exec("nbtstat -A "+this.ip);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String s = "";
			while ((s = inputStream.readLine()) != null) {
				if(s.contains("<20>") && s.contains("UNIQUE")) {
					Pattern r = Pattern.compile("(?:\\s+)([A-Za-z0-9\\-]+)(?:\\s+)(?:<20>.+)");
					Matcher m = r.matcher(s);
					if(m.find()) {
						this.name = m.group(1);
					}
				}
				if(s.contains("MAC Address")) {
					Pattern r = Pattern.compile("(?:\\s+)(?:MAC Address = )([0-9A-F\\-]+)");
					Matcher m = r.matcher(s);
					if(m.find()) {
						this.mac = m.group(1);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
