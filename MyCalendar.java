/**
 * Name: Josh Yang
 * PID: A16667394
 * Email: jwyag@ucsd.edu
 * Sources: None
 * 
 * This file contians the MyCalendat class which constrcuts a treemap for a 
 * calendar for real world use.
 */

 /**
  * This class implements a treemap and has method book. There is an instance
  * variable calendar which is a treemap used to book events.
  */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * No-arg contructor for MyTreeMap calendar
     */
    public MyCalendar() {
        this.calendar = new MyTreeMap<>();
    }
    /**
     * checks if we can book an event with the given start and end times
     * @param start start time of event, this is the key of node
     * @param end end time of event, this is the value of node
     * @return true once event is added
     * @return false if event cannot be added
     */
    public boolean book(int start, int end) {
        if (start < 0 || start >= end) throw new IllegalArgumentException();
        // handle case if floor is null
        if (calendar.floorKey(start) == null){
            if (calendar.ceilingKey(start) == null){
                calendar.put(start,end);
                return true;
            }
            else if (end > calendar.ceilingKey(start)) return false;
            else{
                calendar.put(start,end);
                return true;
            }
            
        }

        // handle case if ceiling is null
        else if (calendar.ceilingKey(start) == null){
            if (calendar.floorKey(start) == null){
                calendar.put(start, end);
                return true;
            }
            else if (start < calendar.get(calendar.floorKey(start))) return false;
            else{
                calendar.put(start, end);
                return true;
            }
        }
        else{
            // if start is ever less than the end value of the floor, false
            if (start < calendar.get(calendar.floorKey(start))) return false;
            // if end is ever less than the end value of floor, false
            if (end < calendar.get(calendar.floorKey(start))) return false;
            // if end is ever greater than the start of the ceiling, false
            if (end > calendar.ceilingKey(start)) return false;
            else{
                calendar.put(start,end);
                return true;
            }
        }
    

    }

    /**
     * getter for calendar
     * @return calendar
     */
    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}