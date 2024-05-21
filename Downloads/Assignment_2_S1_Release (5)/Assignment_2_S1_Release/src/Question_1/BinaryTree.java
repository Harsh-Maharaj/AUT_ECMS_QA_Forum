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

import java.util.ArrayList;
import java.util.Collections;

/**
 * Generic binary tree class to manage a binary tree with elements and comparable keys.
 */
public class BinaryTree<E, F extends Comparable<F>> {
    private Node<E, F> root;
    private int number_of_nodes;
    private ArrayList<Node<E, F>> nodeList;
    private boolean reverseTraversal = false;

    public BinaryTree() {
        this.root = null;
        this.number_of_nodes = 0;
        this.nodeList = new ArrayList<>();
    }

    /**
     * Adds an element with a key to the binary tree.
     * @param element The element to add.
     * @param key The key associated with the element.
     */
    public void addElement(E element, F key) {
        Node<E, F> newNode = new Node<>(key, element);
        if (root == null) {
            root = newNode;
        } else {
            addNode(root, newNode);
        }
        number_of_nodes++;
    }

    
    /**
     * Collects nodes in sorted order into an ArrayList, depending on the traversal order.
     */
    public ArrayList<E> toSortedList() {
        nodeList.clear(); // Clear the list to prepare for a fresh collection of nodes
        collectNodes(root); // Collect nodes in the specified traversal order
        ArrayList<E> sortedList = new ArrayList<>();
        for (Node<E, F> node : nodeList) {
            sortedList.add(node.element);
        }
        return sortedList;
    }

    /**
     * Helper method to collect nodes from the tree into nodeList.
     * @param node The starting node for collection.
     */
    private void collectNodes(Node<E, F> node) {
        if (node != null) {
            if (!reverseTraversal) {
                collectNodes(node.left);
                nodeList.add(node);
                collectNodes(node.right);
            } else {
                collectNodes(node.right);
                nodeList.add(node);
                collectNodes(node.left);
            }
        }
    }
    /**
     * Recursive method to add a node to the binary tree.
     * @param current The current node in the recursion.
     * @param newNode The new node to add.
     */
    private void addNode(Node<E, F> current, Node<E, F> newNode) {
        if (newNode.key.compareTo(current.key) < 0) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                addNode(current.left, newNode);
            }
        } else if (newNode.key.compareTo(current.key) > 0) {
            if (current.right == null) {
                current.right = newNode;
            } else {
                addNode(current.right, newNode);
            }
        }
    }

    /**
     * Toggles the order of traversal between normal and reverse.
     */
    public void reverseOrder() {
        reverseTraversal = !reverseTraversal;
    }

    /**
     * Searches for an element by its key.
     * @param key The key of the element to find.
     * @return The element associated with the key if found; otherwise, null.
     */
    public E searchElement(F key) {
        Node<E, F> result = searchNode(root, new Node<>(key, null));
        return result != null ? result.element : null;
    }

    /**
     * Recursively searches for a node in the tree.
     * @param current The current node in the search.
     * @param target The target node to find (contains only the key).
     * @return The node if found; otherwise, null.
     */
    private Node<E, F> searchNode(Node<E, F> current, Node<E, F> target) {
        if (current == null) {
            return null;
        } else if (target.key.compareTo(current.key) == 0) {
            return current;
        } else if (target.key.compareTo(current.key) < 0) {
            return searchNode(current.left, target);
        } else {
            return searchNode(current.right, target);
        }
    }

    /**
     * Traverses the tree and prints the nodes in sorted order, depending on the reverseTraversal flag.
     * @param node The starting node for traversal.
     */
    private void traversal(Node<E, F> node) {
        if (node != null) {
            if (!reverseTraversal) {
                traversal(node.left);
                System.out.println(node.element);
                traversal(node.right);
            } else {
                traversal(node.right);
                System.out.println(node.element);
                traversal(node.left);
            }
        }
    }

    public void startTraversal() {
        traversal(root);
    }

    /**
     * Node class for BinaryTree.
     */
    private static class Node<E, F> {
        F key;
        E element;
        Node<E, F> left, right;

        Node(F key, E element) {
            this.key = key;
            this.element = element;
            this.left = this.right = null;
        }
    }
}
