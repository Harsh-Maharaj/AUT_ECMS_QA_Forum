/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.util.Date;

public class Memo {
    Date date;
    String title;
    private String message;

    public Memo(Date date, String title, String message) {
        this.date = date;
        this.title = title;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return date.toString() + "\n" + title + "\n" + message;
    }
}
