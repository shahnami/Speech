/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package speech.database;

import java.util.List;

/**
 *
 * @author namishah
 */
public interface Log {
    public void insertLog(String content, String author);
    public void saveLog(int id, String content);
    public void deleteLog(int id);
    public String getLogById(int id);
    public List<String> getAllLogsFormat();         //format date | time
    public List<String> getLogsByDate(String date); //format dd/mm/yyyy
    public List<String> getLogsByTime(String time); //format hh/mm
    public List<String> getLogsByPartialContent(String content);
    
}
