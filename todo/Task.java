package todo;

import java.time.LocalDate;

public class Task {

  private int id;
  private String title = "";
  private String note = "";
  private LocalDate date;
  private boolean done = false;

  public Task(int id, String title, boolean done) {
    this.id = id;
    this.title = title;
    this.done = done;
  }

  public Task(int id, String title, boolean done, LocalDate date) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.done = done;
  }

  public Task(int id, String title, boolean done, LocalDate date, String note) {
    this.id = id;
    this.title = title;
    this.note = note;
    this.date = date;
    this.done = done;
  }

  public static Task create(String title, LocalDate date, String note) {
    if(date != null) { return new Task(1, title, false, date); }
    if(note != "") { return new Task(1, title, false, date, note); }
    return new Task(1, title, false);
  }

  public int getId() { return this.id; }
  public String getTitle() { return this.title; }
  public boolean getDone() { return this.done; }
  public LocalDate getDate() { return this.date; }
  public String getNote() { return this.note; }

  public void setDone(boolean done) { this.done = done; }
  public void setNote(String note) { this.note = note; }
  public void setTitle(String title) { this.title = title; }
  public void setDate(LocalDate date) { this.date = date; }
}