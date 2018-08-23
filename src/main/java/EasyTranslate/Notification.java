package EasyTranslate;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.net.MalformedURLException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class Notification {
	private String txt = "";
	private String trans = "";
	private String user = "";

	public Notification(String txt, String user) {
		String temp = "";
		if (txt.length() > 25) {
			for (int i = 0; i < 25; i++) {
				temp += txt.charAt(i);
			}
			this.txt = temp + "...";
		} else
			this.txt = txt;
		try {
			if (GoogleTranslate.detectLanguage(txt).equals("vi") == false) {
				System.out.println(GoogleTranslate.detectLanguage(txt));
				this.trans = (String) GoogleTranslate.translate("vi", txt.replaceAll("\\n", " ").toLowerCase());
			} else
				this.trans = (String) GoogleTranslate.translate("en", txt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.user = user;
	}

	public void displayTray() throws AWTException, MalformedURLException {
		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit()
				.createImage("C:\\Users\\" + user + "\\Documents\\EasyTranslate\\icon.png");
		TrayIcon trayIcon = new TrayIcon(image);
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("Easy Translate");
		tray.add(trayIcon);
		trayIcon.displayMessage(this.txt, this.trans, MessageType.NONE);
	}
}
