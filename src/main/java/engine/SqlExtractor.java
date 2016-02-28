/**
 * 
 */
package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;

import utils.Utils;

/**
 * 
 * @author mamsow
 *
 */
public class SqlExtractor {

	/**
	 * Read Excel file.
	 * 
	 * @param path
	 *            Excel file path name
	 */
	public void readExcelFile(String path) {
		try {
			FileInputStream xlsFile = Utils.readFile(path);

			final Workbook workbook = WorkbookFactory.create(xlsFile);
			System.out.println("Number of sheets: "
					+ workbook.getNumberOfSheets());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
