package sample;

import java.awt.*;
import java.net.MalformedURLException;

public class TryIconDemo {

    public static void main(String[] args) throws AWTException, MalformedURLException {
        if (SystemTray.isSupported()) {
            TryIconDemo td = new TryIconDemo();
            td.displayTray("hello");
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray(String s) throws AWTException, MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(s, "notification demo", TrayIcon.MessageType.INFO);
    }
}