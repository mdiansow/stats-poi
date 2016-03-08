package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import stats.MainClass;

public class Utils {
	private static Properties properties;

	private static Logger uLogger = MainClass.MAIN_LOGGER;

	public static Properties loadProperties(String path) {
		try {
			// String path = new File(".").getCanonicalPath();
			// String dir = new File(path).getParent();
			//
			// String prop = dir + "\\conf/main.properties";

			FileInputStream file = new FileInputStream(path);

			// to load application's properties,
			properties = new Properties();

			// load all the properties from this file
			properties.load(file);

			// close the file
			file.close();

			System.err.println("Properties size\t" + properties.size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			uLogger.fatal(e.getStackTrace(), e);
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 
	 * @param props
	 * @return
	 */
	public static List<String> sqlRequestsKeys(Properties props) {
		ArrayList<String> sqlRequestKeys = new ArrayList<String>();
		int propsSize = props.size();

		if (propsSize > 0) {
			Enumeration<?> names = props.propertyNames();
			while (names.hasMoreElements()) {
				Object object = (Object) names.nextElement();
				System.err.println("Key  " + object);
				if (object.toString().contains(Constant.SQL_REQUEST_KEY)) {
					sqlRequestKeys.add(object.toString());
				}
			}
		}

		return sqlRequestKeys;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static FileInputStream readFile(String path) throws IOException,
			FileNotFoundException {
		String currentDir = new File(".").getCanonicalPath();

		Path parent = Paths.get(currentDir).getParent();

		System.out.println("Parent " + parent + "  Current " + currentDir);

		File confFolder = new File(parent + "/conf");
		listFilesForFolder(confFolder);

		String prop = currentDir + path;

		FileInputStream file = new FileInputStream(prop);

		return file;
	}

	public static List<String> listFilesForFolder(final File folder) {
		List<String> listOfFiles = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				String fileName = fileEntry.getName();
				listOfFiles.add(fileName);
				uLogger.info("File Entry:\t" + fileName);
				System.err.println("File\t" + fileEntry.getName());
			}
		}
		return listOfFiles;
	}

	public void listf(String directoryName, List<File> files) {

		File directory = new File(directoryName);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				files.add(file);
			} else if (file.isDirectory()) {
				listf(file.getAbsolutePath(), files);
			}
		}
	}
}
