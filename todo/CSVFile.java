package todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.time.LocalDate;

// A class for reading and writing a CSV file, to make it
// easier for myself in the long run
public class CSVFile {

  private String name;
  private File file;

  public CSVFile(String name) {
    this.name = name;
    this.file = new File(name);
  }

  public CSVFile(File f) {
    this.name = f.getName();
    this.file = f;
   }

  public ArrayList<Task> load() {
    ArrayList<Task> tempList = new ArrayList<Task>();
    BufferedReader br = null;
    String line = "";
    int tempId = 0;
    String tempTitle = "";
    boolean tempDone = false;
    LocalDate tempDueDate = null;
    String tempNote = "";

    try {
      br = new BufferedReader(new FileReader(this.name));
      while((line = br.readLine()) != null) {
        String[] tempLine = line.split(",");
        tempId = Integer.parseInt(tempLine[0]);
        tempTitle = tempLine[1];
        tempDone = tempLine[2] == "1" ? true : false;
        if(tempLine.length == 4) {
          tempDueDate = LocalDate.parse(tempLine[2]);
          tempList.add(new Task(tempId, tempTitle, tempDone, tempDueDate));
        }
        else if(tempLine.length == 5) {
          tempNote = tempLine[3];
          tempList.add(new Task(tempId, tempTitle, tempDone, tempDueDate, tempNote));
        }
        else { tempList.add(new Task(tempId, tempTitle, tempDone)); }
        }
      }
      catch(IOException e) { e.printStackTrace(); }

      return tempList;
    }

    public boolean save(ArrayList<Task> taskSource) {
      if(taskSource == null) {
        return false;
      } else {
        String tempString = "";
        for(Task task : taskSource) {
          tempString += (Integer.toString(task.getId()) + ",");
          tempString += (task.getTitle() + ",");
          tempString += (task.getDone() ? "1" : "0" + ",");
          if (task.getDate() != null) tempString += (task.getDate().toString() + ",");
          if (task.getNote() != "") tempString += (task.getNote() + ",");
          tempString += "\n";
        }
        try {
          FileWriter fstream = new FileWriter(file, false);
          BufferedWriter output = new BufferedWriter(fstream);
          output.write(tempString);
          output.close();
          return true;
        } catch(IOException e) {
          e.printStackTrace();
        }
      }
      return false;
    }
  }
