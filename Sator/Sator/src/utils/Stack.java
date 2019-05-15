package utils;

public class Stack<E> {
    private Node<E> first;
    
    public Stack(){
        this.first=null;
    }
    public void push(E value){
        if(first == null){
            first = new Node<>(value);
        }else{
            Node<E> node = new Node<>(value);
            node.setNext(first);
            this.first = node;
        }
    }
    public E peek(){
        return first == null ? null : first.getValue();
    }
    public void editTop(E value){
        if(first != null){
            first.setValue(value);
        }
    }
    public E pop(){
        if(first!=null){
            Node<E> node = first;
            first = first.getNext();
            node.setNext(null);
            return node.getValue();
        }
        return null;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public void clear(){
        while(!isEmpty())
            pop();
    }
}
