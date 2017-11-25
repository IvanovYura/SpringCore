package common;

import java.text.DateFormat;
import java.util.Date;

public class Event {

    private int id;
    private String msg;

    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    void setMsg(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return this.msg;
    }

    public String toString() {
        return String.format("id: %d, date: %s, message: %s", id, df.format(date), msg);
    }

}
