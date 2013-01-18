package upload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Damiano
 */
public class UploadFile {
    
    public void uploadFile(HttpServletRequest request, String dirName) throws IOException{
        String name,filename;
        MultipartRequest multi =
                new MultipartRequest(request, dirName, 10*1024*1024);
        /*try {
            // Use an advanced form of the constructor that specifies a character
            // encoding of the request (not of the file contents) and a file
            // rename policy.
            

            /* Enumeration params = multi.getParameterNames();
            while (params.hasMoreElements()) {
                name = (String)params.nextElement();
                value = multi.getParameter(name);
            } */
            
            /*Enumeration files = multi.getFileNames();
            while (files.hasMoreElements()) {
                name = (String)files.nextElement();
                filename = multi.getFilesystemName(name);
                // String originalFilename = multi.getOriginalFileName(name);
                // String type = multi.getContentType(name);
                File f = multi.getFile(name);
                
                if (f != null) {
                    return true;
                }
            }
        } catch (IOException IEx) {
            return false;
        }*/ 
        
        
        
    }
}
