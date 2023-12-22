/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

public class MyDeque<E> implements DequeInterface<E> {
   
    private static final int DEFAULTCAPACITY = 10;
    private static final int TWO = 2;
   
    Object[] data;
    int size;
    int rear;
    int front;

    public MyDeque(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    public int size(){
        return size;
    }

    public void expandCapacity(){
        int capacity = data.length;
        if(data.length == 0){
            capacity = DEFAULTCAPACITY;
            Object[] newData = new Object[capacity];
            data = newData;
            return;
        }
        if(size == 0){
            Object[] newData = new Object[data.length * 2];
            data = newData;
            return;
        }
        capacity = capacity * TWO;
        Object[] newData = new Object[capacity];
        for(int i = 0; i < size; i++){
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        rear = size - 1;
    }

    public void addFirst(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(data.length == size){
            expandCapacity();
        }
        if(size > 0){
            front = (front - 1 + data.length) % data.length;
        }
        data[front] = element;
        size++;
    }

    public void addLast(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(data.length == size){
            expandCapacity();
        }
        if(size > 0){
            rear = (rear + 1) % data.length;
        }
        data[rear] = element;
        size++;
    }

    public E removeFirst(){
        if(size == 0){
            return null;
        }
        E eData = (E) data[front];
        data[front] = null;
        size--;
        if(size > 0){
            front = (front + 1) % data.length;
        }

        return eData;
    }

    public E removeLast(){
        if(size == 0){
            return null;
        }
        E eData = (E) data[rear];
        data[rear] = null;
        size--;
        if(size > 0){
            rear = (rear - 1 + data.length) % data.length;
        }
        
        return eData;
    }

    public E peekFirst(){
        if(size == 0){
            return null;
        }
        return (E) data[front];
    }

    public E peekLast(){
        if(size == 0){
            return null;
        }
        return (E) data[rear];
    }
}