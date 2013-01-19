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
    
    public CreateExcel(List<Vendita> list) throws FileNotFoundException, IOException{
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet");
        HSSFRow row;
        HSSFCell cell;
        HSSFCell cell2;
        
        for (Vendita v : list){
            row = sheet.createRow(list.size());            
            for(int cellsNumber = 0; cellsNumber < 2; cellsNumber++){
                cell = row.createCell(0);
                cell2 = row.createCell(1);
                cell.setCellValue(v.getId_compratore()); 
                cell2.setCellValue(v.getTasse_vendita());
            }
            
        }
        
        FileOutputStream fileOut = new FileOutputStream("tasse_utenti.xls");
        wb.write(fileOut);
        fileOut.close();                
    }
}
