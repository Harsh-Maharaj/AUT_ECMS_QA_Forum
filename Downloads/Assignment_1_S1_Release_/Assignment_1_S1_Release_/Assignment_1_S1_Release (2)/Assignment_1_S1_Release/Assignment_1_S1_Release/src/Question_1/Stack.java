package Question_1;

public class Stack<E extends Comparable<E>> {

    private final LinkedList<E> stack = new LinkedList<>();

    // Method to push an element onto the stack
    public void push(E data) {
        stack.addHead(data);
    }

    // Method to pop the top element from the stack and return it
    public E pop() {
        if (stack.size == 0) {
            return null; // Return null if stack is empty
        }
        return stack.removeFromHead().data;
    }

    // Method to print all elements in the stack
    public void printStack() {
        stack.printLinkedList();
    }

    // Method to get the size of the stack
    public int getSize() {
        return stack.size;
    }

    int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
