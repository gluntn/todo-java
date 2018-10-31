package todo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Todo {
  private static ArrayList<Task> todoList = new ArrayList<Task>();
  private static CSVFile inputFile;
  private static Scanner scanner;
  public static void main(String[] args) {
    File f = new File("todo.csv");
    scanner = new Scanner(System.in);
    String command = "";
    if(f.exists() && !f.isDirectory()) {
      inputFile = new CSVFile(f);
      todoList = inputFile.load();
    } else {
      System.out.println("No file...\nCreating new file with name 'todo.csv'");
      // create new file!
      try { 
        f.createNewFile();
      } 
      catch(IOException e) {
        System.out.println("Failed to create new file!");
        scanner.close();
        return;
      }
    }

    while(command != "exit") {
      System.out.print("Command: ");
      command = scanner.next();
      switch(command) {
        case "print": print(); break;
        case "add":   add(); break;
        case "remove": remove(); break;
        case "edit": edit(); break;
        case "do": done(); break;
        case "help": help(); break;
        default: break;
      }
    }
    scanner.close();
  }

  public static void print() {
    todoList.forEach((Task task) -> {
      System.out.printf("[%s] %s ", task.getDone() ? "x":" ", task.getTitle());
      if(task.getDate() != null) {
        System.out.printf("Due the %s", task.getDate().toString());
      }
      if(task.getNote() != "") {
        System.out.println("  " + task.getNote());
      }
      System.out.println();
    });
    System.out.println();
  }

  public static void add() {
    System.out.print("Title: ");
    String tempTitle = scanner.nextLine();
    todoList.add(Task.create(tempTitle, null, ""));
    if(inputFile.save(todoList)) {
      System.out.println("Saved successfully!");
    }
    else {
      System.out.println("Error occured!");
    }
  }

  public static void remove() {
    System.out.printf("Which index would you like to remove? ");
    int indexTodo = scanner.nextInt();
    if(indexTodo - 1 > todoList.size()) {
      System.out.println("Out of index!");
      remove();
    }
    todoList.remove(indexTodo - 1);
    inputFile.save(todoList);
  }

  public static void edit() {
    System.out.printf("Which index would you like to edit? ");
    int indexTodo = scanner.nextInt();
    if(indexTodo - 1 > todoList.size()) {
      System.out.println("Out of index!");
      edit();
    }
    System.out.printf("Write new title for %s: ", todoList.get(indexTodo - 1).getTitle());
    todoList.get(indexTodo - 1).setTitle(scanner.nextLine());
    inputFile.save(todoList);
  }

  public static void done() {
    // For debug purposes, just use index
    System.out.printf("Which index would you like to set to done? ");
    int indexTodo = scanner.nextInt();
    if(indexTodo - 1 > todoList.size()) {
      System.out.println("Out of index!");
      done();
    }
    todoList.get(indexTodo - 1).setDone(true);
    inputFile.save(todoList);
  }


  public static void help() {
    System.out.println("\n-- HELP START --");
    System.out.println("   Print(): Prints the current list");
    System.out.println("   Add(): Adds a new item to the list");
    System.out.println("   Remove(): Removes a given item from the list");
    System.out.println("   Edit(): Edit a given item");
    System.out.println("   Do(): Marks an item done");
    System.out.println("   Help(): Shows this help page");
    System.out.println("-- HELP END --\n");
  }

}