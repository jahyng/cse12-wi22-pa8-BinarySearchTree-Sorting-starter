import javax.swing.text.rtf.RTFEditorKit;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    public MyCalendar() {
        this.calendar = new MyTreeMap<>();
    }
    
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

    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}