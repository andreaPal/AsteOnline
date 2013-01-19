package excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Utente;
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
    
    public CreateExcel(List<Utente> users, HttpServletRequest request, String contextPath) throws FileNotFoundException, IOException{
       
        String dir = request.getContextPath() + "/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }
        
        //File file = new File(request.getContextPath() + "/esempio.xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet");
        HSSFRow row;
        HSSFCell cell;
        HSSFCell cell2;
        
        for (int i=0; i<users.size();i++){
            row = sheet.createRow(i);            
            for(int cellsNumber = 0; cellsNumber < 2; cellsNumber++){
                cell = row.createCell(0);
                cell2 = row.createCell(1);
                cell.setCellValue(users.get(i).getNome()); 
                cell2.setCellValue(users.get(i).getTasse());
            }
        }
        FileOutputStream fileOut = new FileOutputStream(contextPath + "/tasse.xls");
        wb.write(fileOut);
        fileOut.close();                
    }
}
