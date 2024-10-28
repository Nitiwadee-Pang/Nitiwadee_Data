package Radix_sort;
public class QueueLink implements Queue {
    class Node {
        Object data;
        Node next;
        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front, rear;
    private int size;

    public QueueLink() {
        front = rear = null;
        size = 0;
    }

    @Override
    public void enqueue(Object value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        Object value = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return value;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return front.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void show() {
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
