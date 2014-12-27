package speech.gui;

import com.sun.glass.events.KeyEvent;

/**
 *
 * @author namishah
 */
public interface Constants {
    public final String AUTHOR = "Nami Shah";
    public final String DRIVER = "com.mysql.jdbc.Driver";
    public final String URL = "jdbc:mysql://localhost/Log";
    public final String USER = "root";
    public final String PASS = "root";
    public final String PATH_TO_PROFILES = System.getProperty("user.home")+"/Desktop/Speech/profiles";
    public final int KEY_1 = KeyEvent.VK_SHIFT;
    public final int KEY_2 = KeyEvent.VK_F3;
    /* ON UPDATE => THIS SHOULD BE THE SAME AS GITHUB RELEASE VERSION */
    public final String VERSION = "1.0.1";
    public final String VERSION_URL = "https://github.com/ShahNami/Speech/tags";
    public final String DOWNLOAD_FILE_URL = "https://github.com/ShahNami/Speech/releases/download/";
    public final String DOWNLOAD_FILE_NAME = "Speech";
    public final String DOWNLOAD_FILE_TYPE = ".zip";
    public final String DOWNLOAD_FILE_PATH = System.getProperty("user.home")+"/Desktop/";
}
