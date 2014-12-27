package speech.updater;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import speech.gui.Constants;

/**
 *
 * @author namishah
 */


/**
 * ADD THIS TO CONSTANTS FILE 
 * [EXAMPLE]
 * 
    public final String VERSION = "1.0.2";
    public final String VERSION_URL = "https://github.com/ShahNami/Speech/tags";
    public final String DOWNLOAD_FILE_URL = "https://github.com/ShahNami/Speech/releases/download/";
    public final String DOWNLOAD_FILE_NAME = "Speech";
    public final String DOWNLOAD_FILE_TYPE = ".zip";
    public final String DOWNLOAD_FILE_PATH = System.getProperty("user.home")+"/Desktop/";
 */

public class Updater {
    private static Updater instance = null;
    private String newVersion = "0";
    
    public static Updater getInstance(){
        if(instance == null){
            instance = new Updater();
        }
        return instance;
    }
    private boolean needsUpdate() throws IOException {
        //Get current Version Number
        Document doc = Jsoup.connect(Constants.VERSION_URL).ignoreContentType(true).timeout(10 * 1000).get();
        Elements tagList = doc.select(".tag-name");
        //Check if the version on github is higher
        if(isHigherVersion(Constants.VERSION, tagList.first().html())){
            newVersion = tagList.first().html();
            return true;
        }
        return false;
    }
    
    private boolean isHigherVersion(String current, String tocheck){
        int cur = Integer.parseInt(current.replace(".", ""));
        int tc = Integer.parseInt(tocheck.replace(".", ""));
        if(tc > cur){
            return true;
        }
        return false;
    }
    
    public boolean downloadUpdate() throws IOException{
        if(needsUpdate()){
            String url = Constants.DOWNLOAD_FILE_URL + newVersion + "/" + Constants.DOWNLOAD_FILE_NAME + Constants.DOWNLOAD_FILE_TYPE;
            download(url, Constants.DOWNLOAD_FILE_PATH+Constants.DOWNLOAD_FILE_NAME+Constants.DOWNLOAD_FILE_TYPE);
            return true;
        }
        return false;
    }
    
    
    private static void download(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
    
    private Updater(){
        //
    }
}
