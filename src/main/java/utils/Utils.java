package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.chainsaw.Main;

import stats.MainClass;

public class Utils {
	private static Properties properties;

	private static Logger uLogger = MainClass.MAIN_LOGGER;

	public static Properties loadProperties(String path) {
		try {
			FileInputStream file = readFile(path);

			// to load application's properties,
			properties = new Properties();

			// load all the properties from this file
			properties.load(file);

			Set set = properties.keySet();

			Iterator<Set> it = set.iterator();
			while (it.hasNext()) {
				String key = "" + it.next();
				System.out.println(key + " " + properties.getProperty(key));
			}

			// close the file
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
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

	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				uLogger.info("File:\t" + fileEntry.getName());
			}
		}
	}
}
