package com.ab.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.ab.modules.Attendees;
import com.ab.modules.Event;

public class EventManagement {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
    int ch;
    List<Event> c = new ArrayList<Event>();
    List<Attendees> att = new ArrayList<Attendees>();
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    File event = new File("Event.txt");
    File attendees = new File("Attendees.txt");
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    if(event.isFile()){
      ois = new ObjectInputStream(new FileInputStream(event));
      c = (List<Event>)ois.readObject();
      ois.close();
    }
    if(attendees.isFile()){
      ois = new ObjectInputStream(new FileInputStream(attendees));
      att = (List<Attendees>)ois.readObject();
      ois.close();
    }

    do{

      System.out.println("1. Enter a new event");
      System.out.println("2. Show all events");
      System.out.println("3. Sarch for the event");
      System.out.println("4. Delete the event");
      System.out.println("5. Edit the event details");
      System.out.println("6. Add an attendee to an event");
      System.out.println("7. List the attendees attending an event");
      System.out.println("8. Delete an attendee from an event");
      System.out.println("0. Exit");
      System.out.print("Please enter your choice: ");

      ch = scanner.nextInt();
      String evName;
      int evID;
      String evDate;
      String attName;
      String attEvent;
      switch (ch){
        //Insert
        case 1:
          System.out.println("\n");
          System.out.print("Please enter how many event details you want to add: ");
          int empNumb = scanner.nextInt();

          for (int y = 0; y < empNumb; y++) {
  	          System.out.print("Enter event number: ");
  	          evID = scanner.nextInt();

  	          System.out.print("Enter event date in the following format yyyy-mm-dd: ");
  	          evDate = scanner1.nextLine();


              System.out.print("Enter event Name: ");
              evName = scanner1.nextLine();

  	          c.add(new Event(evID, evDate, evName));
            }
          oos = new ObjectOutputStream(new FileOutputStream(event));
          oos.writeObject(c);
          oos.close();
          System.out.println("\n");
        break;
        //Display
        case 2:
          System.out.println("Here's the list of Events \n");
          Iterator<Event> i = c.iterator();
          System.out.println("\n------------------------");

          while (i.hasNext()){
            Event e = i.next();
            System.out.print(e + "\n");
          }
          System.out.println("------------------------");
          System.out.println("\n");
        break;
        //Search
        case 3:
          boolean found = false;
          System.out.println("\n");
          System.out.print("Enter your event number to search: ");
          int evNb = scanner.nextInt();
          System.out.println("------------------------");
          i = c.iterator();
          while (i.hasNext()){
            Event e = i.next();
            if (e.getevID() == evNb){
              found = true;
              System.out.println(e);
            }
          }
          if(!found){
            System.out.println("Not found.");
          }
          System.out.println("------------------------");
          System.out.println("\n");
        break;
        //Delete
        case 4:
          found = false;
          System.out.print("\n");
          System.out.println("Enter your event number to delete: ");
          evNb = scanner.nextInt();
          System.out.println("------------------------");
          i = c.iterator();
          while (i.hasNext()){
            Event e = i.next();
            if (e.getevID() == evNb){
              found = true;
              i.remove();
            }
          }
          if(!found){
            System.out.println("Not found.");
          } else {
            System.out.println("Record has been deleted successfully! ");
            oos = new ObjectOutputStream(new FileOutputStream(event));
            oos.writeObject(c);
            oos.close();
          }
          System.out.println("------------------------");
          System.out.println("\n");
        break;
        //Update
        case 5:
          found = false;
          System.out.println("\n");
          System.out.println("Enter your event number to edit: ");
          evNb = scanner.nextInt();
          System.out.println("------------------------");
          ListIterator<Event>li = c.listIterator();
          while (li.hasNext()){
            Event e = li.next();
            if (e.getevID() == evNb){
              System.out.println("Enter your event name to edit: ");
              evName = scanner1.nextLine();

              System.out.println("Enter your event date to update in the following format YYYY-MM-DD :");
              evDate = scanner1.nextLine();

              li.set(new Event(evNb, evDate, evName));
              found = true;
            }
          }
          if(!found){
            System.out.println("Not found.");
          } else {
            oos = new ObjectOutputStream(new FileOutputStream(event));
            oos.writeObject(c);
            oos.close();
            System.out.println("Record has been updated successfully! ");
          }
          System.out.println("------------------------");
          System.out.println("\n");
        break;
        //Add attendees to an event
        case 6:
          System.out.println("\n");
          System.out.print("Please enter how many attendees you want to add: ");
          int attNb = scanner.nextInt();

          for (int y = 0; y < attNb; y++) {
  	          System.out.print("Enter attendee name: ");
  	          attName = scanner1.nextLine();

  	          System.out.print("Enter event name they are attending: ");
  	          attEvent = scanner1.nextLine();

  	          att.add(new Attendees(attName, attEvent));
            }
          oos = new ObjectOutputStream(new FileOutputStream(attendees));
          oos.writeObject(att);
          oos.close();
          System.out.println("\n");
        break;
        // List an event
        case 7:
          found = false;
          System.out.println("\n");
          System.out.print("Enter your event name to list attendees: ");
          Iterator<Attendees> u = att.iterator();
          attEvent = scanner1.nextLine();
          System.out.println("------------------------");
          u = att.iterator();
          while (u.hasNext()){
            Attendees e = u.next();
            if (e.getAttEvent().equals(attEvent)){
              found = true;
              System.out.println(e);
            }
          }
          if(!found){
            System.out.println("Not found.");
          }
          System.out.println("------------------------");
          System.out.println("\n");
        break;
        //Delete the Attendees from the list
        case 8:
          found = false;
          System.out.print("\n");
          System.out.println("Enter your event number to delete: ");
          attName = scanner1.nextLine();
          System.out.println("------------------------");
          u = att.iterator();
          while (u.hasNext()){
            Attendees e = u.next();
            if (e.getAttName().equals(attName)){
              found = true;
              u.remove();
            }
          }
          if(!found){
            System.out.println("Not found.");
          } else {
            System.out.println("Record has been deleted successfully! ");
            oos = new ObjectOutputStream(new FileOutputStream(attendees));
            oos.writeObject(att);
            oos.close();
          }
          System.out.println("------------------------");
          System.out.println("\n");
        break;
      }
    }while(ch!=0);
  }
}

