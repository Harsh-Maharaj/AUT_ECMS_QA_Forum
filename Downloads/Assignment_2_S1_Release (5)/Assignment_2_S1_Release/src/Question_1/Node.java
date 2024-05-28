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
public class Node <E extends Comparable> implements Comparable <Node<E>> {

    private E key;
    private Node<E> left, right;

    public Node(E key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public Node(E key, Node<E> left, Node<E> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node<E> other) {
        return this.key.compareTo(other.key);
    }

    public E getKey() {
        return key;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setKey(E key) {
        this.key = key;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
}