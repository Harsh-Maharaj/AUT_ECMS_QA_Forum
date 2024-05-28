/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

/**
 *
 * @author xhu
 * @param <E>
 */
public class Node<E extends Comparable<E>> {
    public E data;
    public Node<E> next;
    
    public boolean equals(Node<E> node) {
        if (node == null) {
            return false;
        }
        return this.data.equals(node.data);
    }
    
    public int compareTo(Node<E> node) {
        if (node == null) {
            return 1;
        }
        return this.data.compareTo(node.data);
    }
}
