/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xhu
 */

/**
 * Class to manage memos using two binary trees, sorted by date and title.
 */
public class MemoManager {
    private BinaryTree<Memo, Date> bTreeDate;
    private BinaryTree<Memo, String> bTreeTitle;

    public MemoManager() {
        this.bTreeDate = new BinaryTree<>();
        this.bTreeTitle = new BinaryTree<>();
    }

    public void addMemo(String date, String title, String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date memoDate = dateFormat.parse(date);  // Correct date parsing
            Memo memo = new Memo(memoDate, title, message);  // Creating a new memo
            bTreeDate.addElement(memo, memoDate);  // Adding to date-sorted tree
            bTreeTitle.addElement(memo, title);  // Adding to title-sorted tree
        } catch (ParseException ex) {
            Logger.getLogger(MemoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Memo findMemo(Comparable key) {
        if (key instanceof Date) {
            return bTreeDate.searchElement((Date) key);
        } else if (key instanceof String) {
            return bTreeTitle.searchElement((String) key);
        }
        return null;
    }

    public void reverseOrder() {
        bTreeDate.reverseOrder();
        bTreeTitle.reverseOrder();
    }

    public Memo[] getSortedMemoList(Comparable keyType) {
        if (keyType instanceof Date) {
            return bTreeDate.toSortedList().toArray(new Memo[0]);
        } else if (keyType instanceof String) {
            return bTreeTitle.toSortedList().toArray(new Memo[0]);
        }
        return new Memo[0];  // Handling null case
    }
}

