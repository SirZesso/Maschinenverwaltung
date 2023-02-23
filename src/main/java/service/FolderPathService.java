package service;

import java.io.File;

public class FolderPathService {
    public static String getProgramFolderPath() {
		String folderName = "Maschinenverwaltung";
		String path = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			path = System.getenv("APPDATA") + "\\" + folderName;
		} else if (os.contains("mac")) {
			path = System.getProperty("user.home") + "/Library/Application Support/" + folderName;
		} else {
			System.err.println("Unsupported operating system.");
			System.exit(1);
		}
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}
		return path;
	}
    
}
