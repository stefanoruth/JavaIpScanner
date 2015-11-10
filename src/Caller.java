import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Caller implements Runnable {
	private Thread thread;
	private String name;
	private Table table;

	public Caller(String name, Table table) {
		this.name = name;
		this.table = table;
	}

	@Override
	public void run() {
		try {
			Device pc = new Device(this.name);
			pc.getDeviceInfo();
			
			Display.getDefault().asyncExec(new Runnable(){
				@Override
				public void run() {
					TableItem row = new TableItem(table, SWT.NONE);
					row.setText(0, pc.status());
					row.setText(1, pc.ip);
					row.setText(2, pc.name);
					row.setText(3, pc.mac);
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		if (this.thread == null)
		{
			this.thread = new Thread (this, this.name);
	     	this.thread.start ();
		}
	}

}
