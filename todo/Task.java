package todo;

import java.time.LocalDate;

public class Task {

  private int id;
  private String title = "";
  private String note = "";
  private LocalDate date;
  private boolean done = false;

  public Task() { }

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

  public Task createTask(String title, LocalDate date, String note) {
    return new Task();
  }

  public int getId() { return this.id; }
  public String getTitle() { return this.title; }
  public boolean getDone() { return this.done; }
  public LocalDate getDate() { return this.date; }
  public String getNote() { return this.note; }
}