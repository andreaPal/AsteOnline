package excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Vendita;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Damiano
 */
public class CreateExcel {
    
    private void createExcel(ArrayList<Vendita> list) throws FileNotFoundException, IOException{
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet");
        HSSFRow row = null;
        HSSFCell cell = null;
        
        for(int rowsNumber = 0; rowsNumber < list.size() ; rowsNumber++){
            row = sheet.createRow(rowsNumber);
            for(int cellsNumber = 0; cellsNumber < 2; cellsNumber++){
                cell = row.createCell(0);
                cell.setCellValue(0);
                cell.setCellValue(0);
            }
        }
        FileOutputStream fileOut = new FileOutputStream("tasse_utenti.xls");
        wb.write(fileOut);
        fileOut.close();                
    }
}
