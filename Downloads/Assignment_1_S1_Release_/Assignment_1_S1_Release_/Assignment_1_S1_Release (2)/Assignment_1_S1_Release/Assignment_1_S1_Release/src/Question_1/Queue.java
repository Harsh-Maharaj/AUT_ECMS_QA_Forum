package Question_1;

public class Queue<E extends Comparable<E>> {

    private LinkedList<E> queue = new LinkedList<>();

    // Method to add an element to the end of the queue
    public void enqueue(E data) {
        queue.add(data);
    }

    // Method to remove and return the element at the front of the queue
    public E dequeue() {
        if (queue.size == 0) {
            return null; // Return null if queue is empty
        }
        return queue.removeFromHead().data;
    }

    // Method to print all elements in the queue
    public void printQueue() {
        queue.printLinkedList();
    }

    // Method to get the size of the queue
    public int getSize() {
        return queue.size;
    }
}
