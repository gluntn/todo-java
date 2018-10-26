class Task {

  private int id;
  private String title;
  private String note;
  private int date;

  public Task(String title, int date) {
    this.title = title;
    this.date = date;
  }

  public Task(String title, String note, int date) {
    this.title = title;
    this.note = note;
    this.date = date;
  } 

}