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
import java.util.Stack;

public class DataAnalysis<E extends Comparable> {
    
    private E[] data;
    
    public DataAnalysis(E[] data) {
        this.data = data;
    }
    
public boolean bracketEvaluator() {
    Stack<Character> stack = new Stack<>();
    for (E element : data) {
        char c = element.toString().charAt(0);
        switch (c) {
            case '(':
            case '{':
            case '[':
            case '<': // Add case for '<'
                stack.push(c);
                break;
            case ')':
                if (stack.isEmpty() || stack.pop() != '(') return false;
                break;
            case '}':
                if (stack.isEmpty() || stack.pop() != '{') return false;
                break;
            case ']':
                if (stack.isEmpty() || stack.pop() != '[') return false;
                break;
            case '>': // Add case for '>'
                if (stack.isEmpty() || stack.pop() != '<') return false;
                break;
            default:
                // Ignore all other characters
        }
    }
    return stack.isEmpty(); // If stack is empty, all brackets were properly closed
}
}
