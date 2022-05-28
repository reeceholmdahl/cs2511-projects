package binaryheap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

/**
 * This class implements a binary heap data structure by extending the
 * ArrayList class.
 * It also implements the java.util.Queue interface so that it can be
 * used with the framework problem solver.
 * @author tcolburn
 * @param <E> the type of element stored on this binary heap
 */
public class BinaryHeap<E> extends ArrayList<E> implements Queue<E> {

    /**
     * Creates an empty binary heap with a given capacity and comparator.
     * @param capacity The initial size of the underlying ArrayList object.
     * @param comp A comparator object for comparing keys of binary heap elements.
     */
    public BinaryHeap(int capacity, Comparator<E> comp) {
        super(capacity+1);
        init();
        this.comp = comp;
    }

    /**
     * Getter for the comparator for this binary heap.
     * @return the comparator for this binary heap
     */
    public Comparator<E> getComp() {
        return comp;
    }

    /**
     * Initializes the underlying ArrayList object for use as a binary heap.
     * A null object is added to location 0, which is not used by the heap.
     */
    private void init() {
        add(0, null);
    }

    /**
     * Clears this binary heap by clearing and initializing the underlying
     * ArrayList object.
     */
    @Override
    public void clear() {
        super.clear();
        init();
    }

    /**
     * Returns the current size of this binary heap.  Since the first location 
     * (index 0) of the underlying ArrayList object is not used, the size of the 
     * binary heap is one less than the size of the ArrayList object.
     * @return The binary heap's current size. 
     */
    @Override
    public int size() {
        return super.size()-1;
    }

    /**
     * Returns true if this binary heap is empty, that is, its size is zero.
     * @return Whether this binary heap is empty.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a new element to this binary heap.  At the end of the add,
     * the heap has one more element and the heap property is maintained.
     * @param element The element to add
     * @return true.  In accordance with the Collection interface, returns
     * true since duplicate elements are allowed.
     */
    @Override
    public boolean add(E element) {

	/* You must provide */
        super.add(element);
        heapifyUp();
	
        return true;
    }
    
    private void heapifyUp() {
        heapifyUp(size());
    }
    
    private void heapifyUp(int pos) {
        // parent i/2
        int parentPos = pos / 2;
        
        E parent = super.get(parentPos);
        E child = super.get(pos);
        
        if (parentPos > 0 && comp.compare(child, parent) < 0) {
            super.set(parentPos, child);
            super.set(pos, parent);
            heapifyUp(parentPos);
        }
    }

    /**
     * Removes an element from the root of this binary heap.  After removal,
     * the heap has one less element and the heap property is restored.
     * This method does not override any method in the ArrayList class 
     * (note absence of an index parameter).
     * However, it implements a method in the Queue interface.
     * @return The removed element
     */
    @Override
    public E remove() {

        /* You must provide */
        if (isEmpty()) return null;
        
        if (size() > 1) {
            E rightMostLeaf = super.remove(size());
            E removed = super.set(1, rightMostLeaf);
            heapifyDown();
            return removed;
        } else {
            return super.remove(size());
        } 
    }
    
    private void heapifyDown() {
        heapifyDown(1);
    }
    
    private void heapifyDown(int pos) {
        // left child 2i, right child 2i+1
        int leftPos = 2 * pos;
        int rightPos = 2 * pos + 1;
        
        if (leftPos > size() || rightPos > size()) return;
        
        E parent = super.get(pos);
        E left = super.get(leftPos);
        E right = super.get(rightPos);
        
        if (comp.compare(parent, left) <= 0 && comp.compare(parent, right) <= 0) return;
        
        // left child is less than or equal to right child
        if (comp.compare(left, right) <= 0) {
            super.set(pos, left);
            super.set(leftPos, parent);
            heapifyDown(leftPos);
        } else {
            super.set(pos, right);
            super.set(rightPos, parent);
            heapifyDown(rightPos);
        }
    }

    /**
     * A Comparator object used to compare binary heap elements during its
     * add and remove operations.
     */
    private final Comparator<E> comp;

    /*
    The following are required to complete the implementation of the Queue<E> 
    interface. They are not used in the test.
    */
    
    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
