/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

import java.util.Collection;


public class MyPriorityQueue <E extends Comparable<E>>{
    protected MyMinHeap<E> heap;

    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    public MyPriorityQueue(Collection<? extends E> collection){
        if(collection == null || collection.contains(null)){
            throw new NullPointerException();
        }
        heap = new MyMinHeap<>(collection);
    }

    public void push(E element){
        if(element == null){
            throw new NullPointerException();
        }
        heap.insert(element);
    }

    public E peek(){
        if(heap.data.isEmpty()){
            return null;
        }
        return heap.getMin();
    }

    public E pop(){
        if(heap.data.isEmpty()){
            return null;
        }
        return heap.remove();
    }
    public int getLength(){
        return heap.size();
    }
    public void clear(){
        heap.clear();
    }
}
