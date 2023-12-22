public class MyArrayList<E> implements MyList<E>{

    private static final int ZERO = 0;
    private static final int DEFAULTCAPACITY = 5;   

    Object[] data;
    int size;

    public MyArrayList(){
        data = new Object[5];   
    }
    public MyArrayList(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
    }
    public MyArrayList (E[] arr){
        if(arr == null){
            data = new Object[5];
            return;
        }
        data = arr;
        size = arr.length;
    }
    public void expandCapacity (int requiredCapacity){
        int capacity = data.length;
        if(requiredCapacity < data.length){
            throw new IllegalArgumentException();
        }
        if(data.length == ZERO){
            capacity = DEFAULTCAPACITY;
        }
        else {
            capacity += 3;    
        }
        if(capacity < requiredCapacity){
            capacity = requiredCapacity;
        }
        Object[] newData = new Object[capacity];
        for(int i = 0; i < data.length; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
    public int getCapacity(){
        return data.length;
    }
    public void insert(int index, E element){
        if(index < ZERO || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(data.length == size){
            expandCapacity(data.length +1);
        }
        for(int i = size-1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = element;
        size++;
    }
    public void append(E element){
        if(data.length == size){
            expandCapacity(data.length +1);
        }
        data[size] = element;
        size++;
    }
    public void prepend(E element){
        if(data.length == size){
            expandCapacity(data.length +1);
        }
        for(int i = size; i > ZERO; i--){
            data[i] = data[i-1];
        }
        data[ZERO] = element;
        size++;
    }
    public E get(int index){
        if(index < ZERO || index > size){
            throw new IndexOutOfBoundsException();
        }
        E newVar = (E) data[index];
        return newVar;
    }
    public E set(int index, E element){
        if(index < ZERO || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E newVar = (E) data[index];
        data[index] = element;
        return newVar;
    }
    public E remove(int index){
        if(index < ZERO || index >= size){
            throw new IndexOutOfBoundsException();      
        }
        E newVar = (E) data[index];
        for(int i = index; i < size -1; i++){
            data[i] = data[i+1];
        }
        data[size-1] = null;
        size--;
        return newVar;
    }
    public int size(){
        return size;
    }
}