import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Util {

	static File getCVS() {
		// TODO Auto-generated method stub

		File file = null;

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = chooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}

		return file;
	}

	static WebDriver getBrowser() {

		WebDriver driver;

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--proxy-server=socks5://" + Configurations.proxyHost + ":" + Configurations.proxyPort);

		System.out.println("launching chrome browser");

		System.setProperty(Configurations.driverProperty, Configurations.driverPath + Configurations.driverEXE);

		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Configurations.MAX_TIMEOUT, TimeUnit.SECONDS);

		return driver;
	}
}
