package listener;

import db.DBManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Damiano
 */

public class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String dburl = sce.getServletContext().getInitParameter("dburl");
    try {
        DBManager manager = new DBManager(dburl);
        //pubblico l'attributo per il context
        sce.getServletContext().setAttribute("dbmanager",manager);
    } catch (SQLException ex) {
        Logger.getLogger(getClass().getName()).severe(ex.toString());
        throw new RuntimeException(ex);
     }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBManager.shutdown();
    }
    
}
