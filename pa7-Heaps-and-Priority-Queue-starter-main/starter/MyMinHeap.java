/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

import java.util.ArrayList;
import java.util.Collection;

public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E>{
    protected ArrayList<E> data;

    public MyMinHeap(){
        data = new ArrayList<>();
    }

    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null || collection.contains(null)){
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        for(int i = data.size()-1; i >= 0; i--){
            percolateDown(i);
        }
    }

    protected void swap(int from, int to){
        E nData = data.get(from);
        data.set(from, data.get(to));
        data.set(to, nData);
    }

    protected static int getParentIdx(int index){
        return (index - 1) / 2;
    }

    protected static int getLeftChildIdx(int index){
        return (index * 2) + 1;
    }

    protected static int getRightChildIdx(int index){
        return (index * 2) + 2;
    }

    protected int getMinChildIdx(int index){
        int leftChild = getLeftChildIdx(index);
        int rightChild = getRightChildIdx(index);
        if(leftChild >= data.size()){
            return -1;
        }
        else if(rightChild >= data.size()){
            return leftChild;
        }
        else if(data.get(leftChild).compareTo(data.get(rightChild)) <= 0){
            return leftChild;
        }
        else{
            return rightChild;
        }
    }

    protected void percolateUp(int index){
        int parent = getParentIdx(index);
        while(index > 0 && data.get(index).compareTo(data.get(parent)) < 0){
            swap(index, parent);
            index = parent;
            parent = getParentIdx(index);
        }
    }

    protected void percolateDown(int index){
        int leftChild = getLeftChildIdx(index);
        int rightChild = getRightChildIdx(index);
        int smallerChild;
        if(leftChild >= data.size()){
            return;
        }
        if(rightChild >= data.size() || data.get(leftChild).compareTo(data.get(rightChild)) <= 0){
            smallerChild = leftChild;
        }
        else{
            smallerChild = rightChild;
        }

        if(data.get(index).compareTo(data.get(smallerChild)) > 0){
            swap(index, smallerChild);
            percolateDown(smallerChild);
        }
    }

    protected E deleteIndex(int index){
        E removedIndex = data.get(index);
        if(index == data.size() - 1){
            data.remove(data.size()-1);
            return removedIndex;
        }
        E lastIndex = data.get(data.size()-1);
        data.set(index, lastIndex);
        data.remove(data.size()-1);
        if(index == 0 || lastIndex.compareTo(data.get(getParentIdx(index))) >= 0){
            percolateDown(index);
        }
        else{
            percolateUp(index);
        }
        return removedIndex;
    }

    public void insert(E element){
        if(element == null){
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(data.size() -1);
    }

    public E getMin(){
        if(data.size() == 0){
            return null;
        }
        return data.get(0);
    }

    public E remove(){
        if(data.isEmpty()){
            return null;
        }
        return deleteIndex(0);
    }

    public int size(){
        return data.size();
    }

    public void clear(){
        data = new ArrayList<>();
    }
}