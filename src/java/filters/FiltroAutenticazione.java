/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Utente;



/**
 *
 * @author andrea
 */
public class FiltroAutenticazione implements Filter {
    
    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public FiltroAutenticazione() {
    }    
    
   
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            boolean authorized = false;
            if (request instanceof HttpServletRequest) {
                HttpSession session = ((HttpServletRequest)request).getSession(false);            
                if (session!=null) {
                Utente user = (Utente) session.getAttribute("user");
                

                if (user.getRuolo().equalsIgnoreCase("user"))  {
                    authorized = true;
                }
                
            }
            }
            if (authorized) {
                chain.doFilter(request, response);
                return;
            }
            else {
              filterConfig.getServletContext().getRequestDispatcher("/Error").forward(request, response); 
              
            }

        
            /*if(req.getSession().getAttribute("user")==null) {
            //forward request to login.jsp
                filterConfig.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response); 
                              
            } else {
                
                
            chain.doFilter(request, response);
            }*/

        
        
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiltroAutenticazione:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroAutenticazione()");
        }
        StringBuffer sb = new StringBuffer("FiltroAutenticazione(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
   
    
    
   
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
}
