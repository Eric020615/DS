import java.util.*;

class MyQueue<E>  {
    private java.util.LinkedList<E> list = new java.util.LinkedList<>();

    public MyQueue(ArrayList e){
        this.list=new LinkedList<E>(e);
    }


    public MyQueue(E[] e){
        this.list=new LinkedList<E>(Arrays.asList(e));
    }

    public MyQueue(){
        this.list=new LinkedList<E>();
    }

    public void enqueue(E e){
        list.addLast(e);
    }

    public void enqueue(ArrayList e){
        list.addLast((E) e);
    }


    public E dequeue(){
        return list.removeFirst();
    }

    public E getElement(int i){
        return list.get(i);
    }

    public E peek(){
        return list.peek();
    }

    public int size(){
        return list.size();
    }

    public E get(int i){
        return list.get(i);
    }

    public boolean contains(E e){
        return list.contains(e);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public E poll(){
        E element = list.peek();
        list.removeFirst();
        return element;
    }

    public void set(int i, E element){
        list.set(i, element);
    }

    public void clear(){
        list.clear();
    }
    public void toPrintQueue(){
        for(int i=0;i< list.size();i++) {
            System.out.println((i+1)+" "+list.get(i));
        }
    }
}