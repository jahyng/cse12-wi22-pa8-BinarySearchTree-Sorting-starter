
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    public MyCalendar() {
        this.calendar = new MyTreeMap<>();
    }
    
    public boolean book(int start, int end) {
        if (start < 0 || start >= end) throw new IllegalArgumentException();
        if (this.calendar.search(start) != null) return false;
        else{
            return false;
        }
    }

    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}