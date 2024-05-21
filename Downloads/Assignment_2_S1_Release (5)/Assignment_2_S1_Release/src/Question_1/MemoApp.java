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
public class MemoApp {

    /**
     * @param args the command line arguments
     */
    
    static String message = "";
    
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        int dataSize = 10;
        MemoManager memoManager = new MemoManager();
        String[] dates = new String[dataSize];
           
        int mark = 0; 
        
        int[] titleIndexList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int i = 0; i < 5; i++)
        {
            int swapTo = (int)(Math.random()*(titleIndexList.length));
            int swapFrom = (int)(Math.random()*(titleIndexList.length));
            if(swapTo != swapFrom)
            {
                titleIndexList[swapFrom]+=titleIndexList[swapTo];
                titleIndexList[swapTo] = titleIndexList[swapFrom] - titleIndexList[swapTo];
                titleIndexList[swapFrom] = titleIndexList[swapFrom] - titleIndexList[swapTo];
            }
            
        }

        
        for(int i = 0; i < dataSize; i++)
        {
            int month = (int)(Math.round(Math.random()*11)+1);
            int day = (int)(Math.round(Math.random()*29)+1);
            String date = "2023/"+month+"/"+day;
                       
            memoManager.addMemo(date, "Title "+(titleIndexList[i]<10?"0"+titleIndexList[i]:titleIndexList[i]), "message "+i);
                        
            dates[i] = date;
        }
        
        System.out.println("Sorted by Date:");
                
        Memo[] memoList = memoManager.getSortedMemoList(new Date());
        
        //display the sorted memo list (for your debugging)
        for(int i = 0; i < memoList.length; i++)
        {
            System.out.println(memoList[i]+"\n");
        }
        
        //testing your sort by date
        mark+=checkSortByDate(memoList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        
        System.out.println("\n\nSorted by Title: ");
        memoList = memoManager.getSortedMemoList(new String());
        for(int i = 0; i < memoList.length; i++)
        {
            System.out.println(memoList[i]+"\n");
        }
        mark+=checkSortByTitle(memoList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        
        System.out.print("\n\nFind by Title:");
        mark+=checkFindByTitle(memoManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Title:");
        mark+=checkNotFindByTitle(memoManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);        
        
        System.out.print("\n\nFind by Date:");
        mark+=checkFindByDate(memoManager, dates[3]);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Date:");
        mark+=checkNotFindByDate(memoManager, "2022/01/02");
        System.out.println("*****************************************\nCurrent mark: "+mark);
   
        
        memoManager.reverseOrder();
        
        System.out.println("\n\nSorted by Date after reverse:");
        memoList = memoManager.getSortedMemoList(new Date());
        for(int i = 0; i < memoList.length; i++)
        {
            System.out.println(memoList[i]+"\n");
        }
        mark+=checkSortByDateAfterReverse(memoList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.println("\n\nSorted by Title after reverse:");
        memoList = memoManager.getSortedMemoList(new String());
        for(int i = 0; i < memoList.length; i++)
        {
            System.out.println(memoList[i]+"\n");
        }
        mark+=checkSortByTitleAfterReverse(memoList);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Title:");
        mark+=checkFindByTitle(memoManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Title:");
        mark+=checkNotFindByTitle(memoManager);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Date:");
        mark+=checkFindByDate(memoManager, dates[3]);
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.print("\n\nFind by Date:");
        mark+=checkNotFindByDate(memoManager, "2022/01/02");
        System.out.println("*****************************************\nCurrent mark: "+mark);
        
        System.out.println("\n\n***************************** Testing Report *****************************\n");
        System.out.println("Your overall mark of question 1 is "+ mark+" out of 50\n");
        System.out.println(message);
        
    }
    
    public static int checkSortByDate(Memo[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].date.compareTo(list[i+1].date)<=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 6;
        }
        else
        {
            message+="Failed: checkSortByDate\n";
            return 0;
        }
    }
    
    public static int checkSortByTitle(Memo[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].title.compareTo(list[i+1].title)<=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 7;
        }
        else
        {
            message+="Failed: checkSortByTitle\n";
            return 0;
        }
    }
    
    public static int checkSortByDateAfterReverse(Memo[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].date.compareTo(list[i+1].date)>=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 6;
        }
        else
        {
            message+="Failed: checkSortByDateAfterReverse\n";
            return 0;
        }
    }
    
    public static int checkSortByTitleAfterReverse(Memo[] list)
    {
        int mark = 0;
        for(int i = 0; i < list.length-1; i++)
        {
            if(list[i].title.compareTo(list[i+1].title)>=0)
                mark++;
        }
        
        if(mark == 9)
        {
            return 7;
        }
        else
        {
            message+="Failed: checkSortByTitleAfterReverse\n";
            return 0;
        }
    }   
    
    public static int checkFindByTitle(MemoManager memoManager)
    {
        System.out.println(" Title 03");
        Memo resultMemo = memoManager.findMemo("Title 03");
        System.out.println(resultMemo);
        if(resultMemo.title.equals("Title 03"))
            return 3;
        else
        {
            message+="Failed: checkFindByTitle (Title case: Title 03)\n";
            return 0;
        }
    }
    
    public static int checkNotFindByTitle(MemoManager memoManager)
    {
        System.out.println(" Title 00 (test for not found)");
        Memo resultMemo = memoManager.findMemo("Title 00");
        System.out.println(resultMemo);
        if(resultMemo==null)
            return 3;
        else
        {
            message+="Failed: checkNotFindByTitle (Title case: Title 00)\n";
            return 0;
        }
    }
    
    public static int checkFindByDate(MemoManager memoManager, String date)
    {        
        DateFormat dateFormat = new SimpleDateFormat("yyyyy/MM/dd");
        try {
            Date targetDate = dateFormat.parse(date);
            System.out.println(" "+targetDate);
            Memo resultMemo = memoManager.findMemo(targetDate);
            System.out.println(resultMemo);
            if(resultMemo.date.equals(targetDate))
                return 3;
            else
            {
                message+="Failed: checkFindByDate (Title case: "+date+")\n";
                return 0;
            }
        } catch (ParseException ex) {
            Logger.getLogger(MemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static int checkNotFindByDate(MemoManager memoManager, String date)
    {        
        DateFormat dateFormat = new SimpleDateFormat("yyyyy/MM/dd");
        try {
            Date targetDate = dateFormat.parse(date);
            System.out.println(" "+targetDate + "(test for not found)");
            Memo resultMemo = memoManager.findMemo(targetDate);
            System.out.println(resultMemo);
            if(resultMemo==null)
                return 3;
            else
            {
                message+="Failed: checkNotFindByDate (Title case: "+date+")\n";
                return 0;
            }
        } catch (ParseException ex) {
            Logger.getLogger(MemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
