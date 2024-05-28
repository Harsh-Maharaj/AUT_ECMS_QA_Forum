package Question_1;

public class LinkedList<E extends Comparable<E>> {

    public int size = 0;
    public Node<E> head;

    // Method to add a new node with data at the beginning of the list
    public void addHead(E data) {
        Node<E> newNode = new Node<>();
        newNode.data = data;
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Method to get the head of the list
    public Node<E> getHead() {
        return head;
    }

    // Method to add a new node with data at the end of the list
    public void add(E data) {
        if (head == null) {
            addHead(data);
        } else {
            Node<E> newNode = new Node<>();
            newNode.data = data;
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            size++;
        }
    }

    // Private method to add a node after the specified head node
    private void add(Node<E> head, Node<E> node) {
        node.next = head.next;
        head.next = node;
        size++;
    }

    // Method to print all elements of the list
    public void printLinkedList() {
        Node<E> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Private method to print elements starting from a given node
    private void printLinkedList(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        printLinkedList(node.next);
    }

    // Method to check if the list contains a node with the given data
    public boolean contains(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Private method to check if the list contains a node with the given data starting from a given node
    private boolean contains(Node<E> head, Node<E> node) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(node.data)) {
            return true;
        }
        return contains(head.next, node);
    }

    // Method to remove the first occurrence of the node with the given data from the list
    public void remove(E data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    // Method to remove the node at the given position in the list
    public void remove(int position) {
        if (position < 0 || position >= size) {
            return;
        }
        if (position == 0) {
            head = head.next;
            size--;
            return;
        }
        Node<E> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    // Private method to remove the node at the given index starting from a given head node
    private void removeByIndex(Node<E> head, int position) {
        if (position < 0 || position >= size) {
            return;
        }
        if (position == 0) {
            head = head.next;
            size--;
            return;
        }
        removeByIndex(head.next, position - 1);
    }

    // Private method to remove a node from the body of the list
    private void removeFromBody(Node<E> head, Node<E> node) {
        while (head != null && head.next != null) {
            if (head.next.equals(node)) {
                head.next = head.next.next;
                size--;
                return;
            }
            head = head.next;
        }
    }

    // Method to remove and return the head node of the list
    public Node<E> removeFromHead() {
        if (head == null) {
            return null;
        }
        Node<E> temp = head;
        head = head.next;
        size--;
        return temp;
    }

    // Method to remove and return the last node of the list
    public Node<E> removeFromTail() {
        if (head == null || head.next == null) {
            return removeFromHead();
        }
        Node<E> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        Node<E> temp = current.next;
        current.next = null;
        size--;
        return temp;
    }

    // Private method to remove the last node of the list starting from a given node
    private Node<E> removeFromTail(Node<E> node) {
        if (node == null || node.next == null) {
            return null;
        }
        if (node.next.next == null) {
            Node<E> temp = node.next;
            node.next = null;
            size--;
            return temp;
        }
        return removeFromTail(node.next);
    }

    // Method to add a node with data in sorted order
    public void addInOrder(E data) {
        if (head == null || head.data.compareTo(data) > 0) {
            addHead(data);
            return;
        }
        Node<E> newNode = new Node<>();
        newNode.data = data;
        Node<E> current = head;
        while (current.next != null && current.next.data.compareTo(data) < 0) {
            current = current.next;
        }
        add(current, newNode);
    }

    // Private method to add a node in sorted order starting from a given node
    private void addInOrder(Node<E> currentNode, Node<E> newNode) {
        while (currentNode.next != null && currentNode.next.data.compareTo(newNode.data) < 0) {
            currentNode = currentNode.next;
        }
        add(currentNode, newNode);
    }

    // Method to get the node at the given index in the list
    public Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // Private method to get the node at the given index starting from a given head node
    private Node<E> getNode(int index, Node<E> head) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return head;
        }
        return getNode(index - 1, head.next);
    }

    // Method to get the data at the given index in the list
    public E getData(int index) {
        Node<E> node = getNode(index);
        if (node != null) {
            return node.data;
        }
        return null;
    }

    // Private method to get the data at the given index starting from a given head node
    private E getData(int index, Node<E> head) {
        Node<E> node = getNode(index, head);
        if (node != null) {
            return node.data;
        }
        return null;
    }
}

