/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;


/**
 *
 * @author xhu
 */
public class LinkedListTest {
    public static void main(String[] args) {
        
        int mark = 0;
        String report = new String("Failed test\n");
        
        int mark_add = 0;

        mark_add = test_add();
        mark += mark_add;
        if(mark_add == 0)
        {
            report += "test_contains()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");

        
        //check for contains method
        int mark_contains = 0;

        mark_contains = test_contains();

        mark += mark_contains;
        if(mark_contains == 0)
        {
            report += "test_contains()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
        int mark_getData = 0;

        mark_getData = test_getData();

        mark += mark_getData;
        if(mark_getData == 0)
        {
            report += "test_getData()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
        //check for remove method
        
        int mark_remove = 0;

        mark_remove = test_remove();

        mark += mark_remove;
        if(mark_remove == 0)
        {
            report += "test_remove()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
         //check for remove from head method
        int mark_remove_head = 0;

        mark_remove_head = test_remove_head();
       
        mark += mark_remove_head;
        if(mark_remove_head == 0)
        {
            report += "test_remove_head()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
        //check for remove from tail method
        int mark_remove_tail = 0;
        
        mark_remove_tail = test_remove_tail();
        
        mark += mark_remove_tail;
        if(mark_remove_tail == 0)
        {
            report += "test_remove_tail()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");              
        
        //part 2
        
        
        int mark_dequeue = 0;

        mark_dequeue = test_Queue();

        mark += mark_dequeue;
        if(mark_dequeue == 0)
        {
            report += "test_Queue()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
        int mark_pop = 0;
        
        mark_pop = test_Stack();
        
        mark += mark_pop;
        if(mark_pop == 0)
        {
            report += "test_Stack()\n";
        }
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
        int mark_bracketEvaluator = 0;
        
        mark += test_bracket_evaluation("{((2 x 5)+(3*-2 + 5))}", true);
        mark += test_bracket_evaluation("{(2 x 5)+(3*-2 + 5))}", false);
        mark += test_bracket_evaluation("List<List<E>>", true);
        mark += test_bracket_evaluation("List<List<E>>>", false);
        mark += test_bracket_evaluation("{(<<eeeek>>){}{…}(e(e)e){hello}}", true);
        mark += test_bracket_evaluation("{(< eeeek>>){}{…}e(e)e){hello}}}", false);
        
        mark += mark_bracketEvaluator;
        if(mark_bracketEvaluator == 0)
        {
            report += "mark_bracketEvaluator()\n";
        }
        
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");
        
        if(report.contains("getData()"))
        {
            System.out.println("Please fix getData() method before testing add_in_order() method.\n");
        }
        else
        {
            int mark_addInOrder = 0;
            
            mark_addInOrder = test_add_in_order();
           
            mark += mark_addInOrder;
            if(mark_addInOrder == 0)
            {
                report += "test_add_in_order()\n";
            }
        }
        
        
        System.out.println("Current mark: " + mark);
        System.out.println("________________________________________________\n");

        int mark_NodeClass = 0;

        mark_NodeClass = test_node();
        
        if(mark_NodeClass == 0)
        {
            report += "test_node()\n";
        }
        if(mark_NodeClass == 5)
        {
            System.out.println("passed");
        }
        mark += mark_NodeClass;
        System.out.println();
        
        
        System.out.println("************************************************\n");
        if(mark < 40)
            System.out.println(report);
        System.out.println("\nTotal mark of Question 1: "+ mark+" out of 50\n");
    }
    
    private static int test_node()
    {
        System.out.println("Testing for \"Node\" Class (mark: 5)");
        int mark = 0;
        Node<String> s_node1 = new Node<>();
        s_node1.data = "testing";
        s_node1.next = null;
        
        Node<String> s_node2 = new Node<>();
        s_node2.data = "testing";
        s_node2.next = null;
        
        Node<Integer> i_node1 = new Node<>();
        i_node1.data = 1;
        i_node1.next = null;
        
        Node<Integer> i_node2 = new Node<>();
        i_node2.data = 2;
        i_node2.next = null;
        
        i_node1.next = i_node2;
        
        if(s_node1.data.equals("testing"))
            mark += 1;
        if(i_node1.next.data == 2)
            mark += 1;
        if(s_node1.equals(s_node2))
            mark += 1;
        if(i_node1.compareTo(i_node2) < 0)
            mark += 2;
        
        return mark;
    }
    
    private static int test_add()
    {               
        System.out.println("Testing for \"add\" method (mark: 3)");
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        if(list.head == null || list.head.next == null|| list.head.next.next == null)
        {
            System.out.println("Failed, mark: 0");
            return 0;
        }
        if(list.head.data.equals("a") && 
                list.head.next.data.equals("b") && 
                list.head.next.next.data.equals("c"))
        {
            System.out.println("passed, mark: 3");
            return 3;
        }
        else
        {
            System.out.println("Failed, mark: 0");
            return 0;
        }
    }
    
    private static int test_contains()
    {
        int mark = 0;
        System.out.println("Testing for \"contains\" method (mark: 2)");
        LinkedList<Character> list = new LinkedList<>();
        list.add('a');
        list.add('b');
        list.add('c');        

        
        //Node<Character> node = new Node<>();
        //node.data = (char)('b');
        if(list.contains('b'))
        {
            mark += 1; 
            System.out.println("Passed, contain(\"b\") True");
        }
        else
        {
            System.out.println("Failed, mark: "+mark);
        }
        
        
        
        //node.data = (char)('A');
        if(list.contains('A'))
        {
            System.out.println("Failed, mark: "+mark);
        }
        else
        {
            mark += mark;
            if(mark > 0)
                System.out.println("Passed, contain(\"A\") False");           
        }
        
        list.printLinkedList();
        if(mark == 2)
        {
            System.out.println("\nPassed, mark 2\n");
        }
        
        return mark;
    }
    
    private static int test_getData()
    {
        System.out.println("Testing for \"getData\" method (mark: 4)");
        int mark = 0;
        LinkedList<Float> list = new LinkedList<>();
        list.add(0.3f);
        list.add(0.5f);
        list.add(1.2f);
        
        Float data = list.getData(mark);
        if(data == null)
        {
            System.out.println("Cannot get data from the list.");
            return 0;
        }
        if(data.compareTo(0.3f) == 0)
            mark += 2;
        else
            System.out.println("Cannot get data from first node.");
        
        if(list.getData(2).compareTo(1.2f) == 0)
            mark += 2;
        else
            System.out.println("Cannot get data from third node.");
        
        if(mark == 4)
        {
            System.out.println("passed, mark: 3");
        }
        else
        {
            System.out.println("Failed, mark: 0");
        }
        return mark;
    }
    
    private static int test_remove()
    {
        System.out.println("Testing for \"remove\" method (mark: 3)");
        int mark = 0;
        LinkedList<Float> list = new LinkedList<>();
        list.add(0.0f);
        list.add(1.1f);
        list.add(2.2f);
        
        System.out.println("Test remove by node:");
        list.printLinkedList();
        
        
        System.out.println("\nRemove node data: 1.1f");
        list.remove(1.1f);
        System.out.println("After remove node data: 1.1f");
        list.printLinkedList();
        
        System.out.println("\nTest remove by index:");
        list.add(3.3f);
        list.add(4.4f);
        list.printLinkedList();
        
        System.out.println("\nRemove node index: 2");
        list.remove(2);
        list.printLinkedList();
        
        if(list.size == 3 
        && list.head.data == 0.0f 
        && list.head.next.data == 2.2f 
        && list.head.next.next.data == 4.4f)
        {
            mark = 3;
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }
        
        
        return mark;
    }
    
    private static int test_remove_head()
    {
        System.out.println("Testing for \"remove from head\" method (mark: 2)");
        LinkedList<String> list = new LinkedList<>();
        int mark = 0;
        list.add("10");
        list.add("20");
        list.printLinkedList();
        
        list.removeFromHead();
        System.out.println();
        list.printLinkedList();
        if(list.size == 1 && list.contains("20"))
        {
            mark = 2;
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }
        
        return mark;
    }
    
    private static int test_remove_tail()
    {
        System.out.println("Testing for \"remove from tail\" method (mark: 2)");
        LinkedList<Integer> list = new LinkedList<>();
        int mark = 0;
        list.add(10);
        list.add(20);
        list.add(30);
        
        list.printLinkedList();
        list.removeFromTail();
        System.out.println();
        list.printLinkedList();
        
        if(list.size == 2 && !list.contains(30))
        {
            mark = 2;
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }        
        
        return mark;
    }
    
    private static int test_Queue()
    {
        System.out.println("Testing for \"Queue\" methods (mark: 5)");
        
        String word = "Hello Data Structures & Algorithms Students.";
        Queue<Character> queue = new Queue();
        
        for(int i = 0; i < word.length(); i++)
        {
            queue.enqueue((Character)word.charAt(i));
        }
        String output = "";
        for(int i = 0; i < word.length(); i++)
        {
            Character char_from_queue = queue.dequeue();
            if(char_from_queue == null)
            {
                System.out.println("dequeue method returns null");
                i = word.length();
            }
            else
            {
                output += char_from_queue;
            }
        }
        
        int mark = 0;
        //for(int i = 0; queue.getSize() > 0 && ((Character)queue.dequeue()).equals(word.charAt(i));i++);
        if(queue.getSize() == 0 && word.equals(output))
        {
            mark = 5;
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }
        return mark;
    }
    
    private static int test_Stack()
    {
        System.out.println("Testing for \"Stack\" methods (mark: 5)");
        Stack<Integer> stack = new Stack();
        
        stack.push(0);
        if(stack.pop()==null)
        {
            System.out.println("Failed");
            return 0;
        }
        
        for(int i = 0; i < 5; i++)
        {
            stack.push(i);
        }
        
        int mark = 0;               
        
        for(int i = 4; i >=0 && stack.pop() == i; i--);
        
        if(stack.getSize() == 0)
        {
            mark = 5;
            System.out.println("Passed");
        }
        else
        {
            System.out.println("Failed");
        }
        return mark;
    }    
    
    private static int test_bracket_evaluation(String testingString, boolean answer)
    {
        System.out.println("Testing: "+testingString + " (mark: 2)");
        int mark = 0;
        char[] chars = testingString.toLowerCase().toCharArray();
        Character[] characters = new Character[chars.length];
        for(int i = 0; i < chars.length; i++)
        {
            characters[i] = chars[i];
        }
        DataAnalysis<Character> da = new DataAnalysis(characters);
        if(da.bracketEvaluator() == answer)
        {
            mark+=2;
            System.out.println("Passed");
        }
        return mark;
    }
    
    
    private static int test_add_in_order()
    {
        System.out.println("Testing for \"add in order\" method (mark: 7)");
        LinkedList<String> string_list = new LinkedList<>();
        int mark = 0;
        string_list.addInOrder("ABC");
        string_list.addInOrder("ABA");
        string_list.addInOrder("BBB");
        string_list.addInOrder("ABB");
        
        System.out.println("List: ");
        string_list.printLinkedList();
        
        if(  string_list.getNode(0).data.equals("ABA") 
           &&string_list.getNode(1).data.equals("ABB") 
           &&string_list.getNode(2).data.equals("ABC") 
           &&string_list.getNode(3).data.equals("BBB"))
        {
            System.out.println("\tString test Passed");
            mark+=3;
        }
        else
        {
            System.out.println("\tString test Failed");
        }
        
        LinkedList<Integer> int_list = new LinkedList();
        int_list.addInOrder(3);
        int_list.addInOrder(2);
        int_list.addInOrder(0);
        int_list.addInOrder(1);
        int_list.addInOrder(12);
        
        System.out.println("List: ");
        int_list.printLinkedList();
        
        if(  int_list.getNode(0).data.equals(0) 
           &&int_list.getNode(1).data.equals(1) 
           &&int_list.getNode(2).data.equals(2)
           &&int_list.getNode(3).data.equals(3)
           &&int_list.getNode(4).data.equals(12))
        {
            System.out.println("\tInteger test Passed");
            mark+=4;
        }
        else
        {
            System.out.println("\tInteger test Failed");
        }
        
        return mark;
    }
}
