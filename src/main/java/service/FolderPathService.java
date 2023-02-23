package service;

import java.io.File;

public class FolderPathService {
	public static String getProgramFolderPath(String subFolder) {
	String folderName = "Maschinenverwaltung";
	String path = "";
	String os = System.getProperty("os.name").toLowerCase();
	if (os.contains("win")) {
	path = System.getenv("APPDATA") + "\\" + folderName + "\\" + subFolder;
	} else if (os.contains("mac")) {
	path = System.getProperty("user.home") + "/Library/Application Support/" + folderName +"/"+ subFolder;
	} else {
	System.err.println("Unsupported operating system.");
	System.exit(1);
	}
	File folder = new File(path);
	if (!folder.exists()) {
	folder.mkdirs();
	}
	return path;
	}
	}
