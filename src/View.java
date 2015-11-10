import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;


public class View {

	protected Shell shell;
	private Table DeviceTable;
	private Text rangeFrom;
	private Text rangeTo;
	private Label textCountIps;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			View window = new View();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		System.out.println("Shell Thread: "+Thread.currentThread().getName());
		Display display = Display.getDefault();
		// Set Window
		shell = new Shell();
		shell.setSize(1000, 600);
		shell.setMinimumSize(600, 470);
		shell.setText("IP App");
		shell.setLayout(new FormLayout());
		// Set Table
		this.createTable();
		// Do Other stuff
		this.createContents();
		// Display Window
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		// Title
		Label title = new Label(shell, SWT.NONE);
		title.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		FormData fd_title = new FormData();
		fd_title.right = new FormAttachment(DeviceTable, -10);
		fd_title.left = new FormAttachment(0, 10);
		fd_title.top = new FormAttachment(0, 10);
		title.setLayoutData(fd_title);
		title.setText("IP Scanner");
		
		// Separator
		Label separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_separator = new FormData();
		fd_separator.left = new FormAttachment(0, 10);
		fd_separator.right = new FormAttachment(DeviceTable, -10);
		fd_separator.top = new FormAttachment(title, 10);
		separator.setLayoutData(fd_separator);
		
		// Text From
		Label textFrom = new Label(shell, SWT.NONE);
		FormData fd_textFrom = new FormData();
		fd_textFrom.right = new FormAttachment(DeviceTable, -120);
		fd_textFrom.left = new FormAttachment(0, 10);
		fd_textFrom.top = new FormAttachment(separator);
		textFrom.setLayoutData(fd_textFrom);
		textFrom.setText("From");
		
		// Range From
		rangeFrom = new Text(shell, SWT.BORDER);
		FormData fd_rangeFrom = new FormData();
		fd_rangeFrom.left = new FormAttachment(0, 10);
		fd_rangeFrom.right = new FormAttachment(DeviceTable, -10);
		fd_rangeFrom.top = new FormAttachment(textFrom, 0);
		rangeFrom.setLayoutData(fd_rangeFrom);
		rangeFrom.setText("10.128.81.120");
		
		// Text To
		Label textTo = new Label(shell, SWT.NONE);
		FormData fd_textTo = new FormData();
		fd_textTo.right = new FormAttachment(DeviceTable, -10);
		fd_textTo.left = new FormAttachment(0, 10);
		fd_textTo.top = new FormAttachment(rangeFrom, 10);
		textTo.setLayoutData(fd_textTo);
		textTo.setText("To");
		
		// Range To
		rangeTo = new Text(shell, SWT.BORDER);
		FormData fd_rangeTo = new FormData();
		fd_rangeTo.left = new FormAttachment(0, 10);
		fd_rangeTo.right = new FormAttachment(DeviceTable, -10);
		fd_rangeTo.top = new FormAttachment(textTo, 0);
		rangeTo.setLayoutData(fd_rangeTo);
		rangeTo.setText("10.128.81.125");
		
		// Button
		Button btnScan = new Button(shell, SWT.NONE);
		FormData fd_btnScan = new FormData();
		fd_btnScan.left = new FormAttachment(0, 50);
		fd_btnScan.right = new FormAttachment(DeviceTable, -50);
		fd_btnScan.top = new FormAttachment(rangeTo, 40);
		btnScan.setLayoutData(fd_btnScan);
		btnScan.setText("Scan");
		
		// Separator2
		Label separator2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_separator2 = new FormData();
		fd_separator2.left = new FormAttachment(0, 10);
		fd_separator2.right = new FormAttachment(DeviceTable, -10);
		fd_separator2.top = new FormAttachment(btnScan, 10);
		separator2.setLayoutData(fd_separator2);
		
		// Count Ips
		textCountIps = new Label(shell, SWT.NONE);
		FormData fd_textCountIps = new FormData();
		fd_textCountIps.top = new FormAttachment(separator2, 10);
		fd_textCountIps.right = new FormAttachment(DeviceTable, -10);
		fd_textCountIps.left = new FormAttachment(0, 10);
		textCountIps.setLayoutData(fd_textCountIps);
		textCountIps.setText("Scanned Ips: 0\nOnline: 0\nOffline: 0");
		
		// Separator3
		Label separator3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_separator3 = new FormData();
		fd_separator3.top = new FormAttachment(textCountIps, 10);
		fd_separator3.left = new FormAttachment(0, 10);
		fd_separator3.right = new FormAttachment(DeviceTable, -10);
		separator3.setLayoutData(fd_separator3);
		
		// Save Data
		Button btnSaveData = new Button(shell, SWT.NONE);
		FormData fd_btnSaveData = new FormData();
		fd_btnSaveData.top = new FormAttachment(separator3, 10);
		fd_btnSaveData.left = new FormAttachment(0, 10);
		fd_btnSaveData.right = new FormAttachment(DeviceTable, -10);
		btnSaveData.setLayoutData(fd_btnSaveData);
		btnSaveData.setText("Save Data");
		
		
		
		// Events
		btnScan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DeviceTable.removeAll();
				scanIps(rangeFrom.getText(), rangeTo.getText());
			}
		});
		
		btnSaveData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveData();
			}
		});
		
		DeviceTable.addListener(SWT.Paint, new Listener(){
			@Override
			public void handleEvent(Event arg0) {
				int numScanned = DeviceTable.getItemCount();
				int numOnline = 0;
				int numOffline = 0;
				
				for (int i = 0; i < numScanned; i++) {
					String colm = DeviceTable.getItem(i).getText(0);
					switch (colm) {
						case "Online":
								numOnline++;
							break;
						case "Offline":
								numOffline++;
							break;
					}
				}
				textCountIps.setText("Scanned Ips: "+numScanned+"\nOnline: "+numOnline+"\nOffline: "+numOffline);
			}
		});
	}
	
	protected void scanIps(String startIp, String endIp)
	{
		System.out.println("Scanning IPs.");
		System.out.println(startIp+" - "+endIp);
		
		String[] from = startIp.split("\\.");
		String[] to = endIp.split("\\.");
		
		// oct1
		for(int oct1 = Integer.parseInt(from[0]); oct1 <= Integer.parseInt(to[0]); oct1++){
			// oct2
			for	(int oct2 = Integer.parseInt(from[1]); oct2 <= Integer.parseInt(to[1]); oct2++){
				// oct3
				for(int oct3 = Integer.parseInt(from[2]); oct3 <= Integer.parseInt(to[2]); oct3++){
					// oct4
					for (int oct4 = Integer.parseInt(from[3]); oct4 <= Integer.parseInt(to[3]); oct4++) {
						// Generate Ip
						String ip = oct1+"."+oct2+"."+oct3+"."+oct4;
						// Make Swing Inisialize Caller
						SwingUtilities.invokeLater(new Runnable(){
							@Override
							public void run() {
								new Caller(ip, DeviceTable).start();
							}
						});
					}
				}
			}
		}
	}
	
	public void saveData()
	{
		XmlStorage storage = new DeviceStorage();
		int numColm = DeviceTable.getColumnCount();
		int numRows = DeviceTable.getItemCount();
		String[][] data = new String[numRows][numColm];
		
		for(int i = 0;i < numRows; i++){
			//System.out.println("## Row ##");
			for(int j = 0; j < numColm; j++){
				//System.out.println("Item: "+DeviceTable.getItem(i).getText(j));
				data[i][j] = DeviceTable.getItem(i).getText(j);
			}
		}
		
		storage.save(data);
	}
	
	
	protected void createTable()
	{
		// Table
		DeviceTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_DeviceTable = new FormData();
		fd_DeviceTable.top = new FormAttachment(0, 10);
		fd_DeviceTable.right = new FormAttachment(100, -10);
		fd_DeviceTable.left = new FormAttachment(0, 200);
		fd_DeviceTable.bottom = new FormAttachment(100, -10);
		DeviceTable.setLayoutData(fd_DeviceTable);
		DeviceTable.setHeaderVisible(true);
		DeviceTable.setLinesVisible(true);
		
		// Columns
		TableColumn tblclmnStatus = new TableColumn(DeviceTable, SWT.NONE);
		tblclmnStatus.setResizable(false);
		tblclmnStatus.setWidth(150);
		tblclmnStatus.setText("Status");
		
		TableColumn tblclmnIp = new TableColumn(DeviceTable, SWT.NONE);
		tblclmnIp.setWidth(150);
		tblclmnIp.setText("IP");
		
		TableColumn tblclmnName = new TableColumn(DeviceTable, SWT.NONE);
		tblclmnName.setWidth(200);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnMacAddress = new TableColumn(DeviceTable, SWT.NONE);
		tblclmnMacAddress.setWidth(200);
		tblclmnMacAddress.setText("Mac Address");
		
		// Rows
		/*
			TableItem row = new TableItem(DeviceTable, SWT.NONE);
			row.setText(0, "Online");
			row.setText(1, "127.0.0.1");
			row.setText(2, "localhost");
			row.setText(3, "Unknown");
		*/
	}
}
