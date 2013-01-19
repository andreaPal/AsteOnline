/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrea
 */
public class UploadFile extends HttpServlet {

    

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MultipartRequest multi = new MultipartRequest(request, getServletContext().getRealPath("/img"),
        10*1024*1024, "ISO-8859-1", new DefaultFileRenamePolicy());
        System.out.println("PARAMS:");
        Enumeration params = multi.getParameterNames();
        while (params.hasMoreElements()) {
            String name = (String)params.nextElement();
            String value = multi.getParameter(name);
            System.out.println(name + "=" + value);
        }

        System.out.println("FILES:");
        Enumeration files = multi.getFileNames();

        while (files.hasMoreElements()) {
            String name = (String)files.nextElement();
            String filename = multi.getFilesystemName(name);
            String originalFilename = multi.getOriginalFileName(name);
            String type = multi.getContentType(name);
            File f = multi.getFile(name);
            System.out.println("name: " + name);
            System.out.println("filename: " + filename);
            System.out.println("originalFilename: " + originalFilename);
            System.out.println("type: " + type);
            if (f != null) {
                System.out.println("f.toString(): " + f.toString());
                System.out.println("f.getName(): " + f.getName());
                System.out.println("f.exists(): " + f.exists());
                System.out.println("f.length(): " + f.length());
            }
            
            response.setContentType("text/html");
            response.getWriter().print(f.getName());
            
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
