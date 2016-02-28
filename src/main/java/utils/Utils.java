package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Utils {
	private static Properties properties;

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

		String dir = new File(currentDir).getParent();

		String prop = currentDir + path;

		FileInputStream file = new FileInputStream(prop);
		return file;
	}
}
