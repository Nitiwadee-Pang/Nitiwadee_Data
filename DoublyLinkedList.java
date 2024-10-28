public class DoublyLinkedList implements Collection {

    class Node {
        Object data;
        Node link;
        Node plink;

        Node(Object d, Node l, Node pl) {
            data = d;
            link = l;
            plink = pl;
        }
    }

    private Node head;
    private Node tail;
    private int count;
    Object [] hashtable;
    int tablesize = 2;
    public DoublyLinkedList() {
        count = 0;
        head = null;
        tail = null;
    }

    @Override
    public void add(Object value) {
        Node newNode = new Node(value, null, tail);
        if (tail != null) {
            tail.link = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
        }

        count++;
    }

    @Override
    public void add(int index, Object value) {
        if (index <= 0 || index > count + 1) {
            throw new IndexOutOfBoundsException("out of bound");
        }
        if (index == count+1) {
            add(value);
        } else if (index == 1) {
            Node newNode = new Node(value, head, null);
            head.plink = newNode;
            head = newNode;
            count++;
        } else {
            Node current = head;
            for (int i = 1; i < index; i++) {
                current = current.link;
            }
            Node newNode = new Node(value, current, current.plink);
            current.plink.link = newNode;
            current.plink = newNode;
            count++;
        }
    }

    @Override
    public Object get(int index) {
        if (index <= 0 || index > count) {
            throw new IndexOutOfBoundsException("out of bound");
        }
        Node current = head;
        for (int i = 1; i < index; i++) {
            current = current.link;
        }
        return current.data;
    }

    @Override
    public void set(int index, Object value) {
        if (index <= 0 || index > count) {
            throw new IndexOutOfBoundsException("out of bound");
        }
        Node current = head;
        for (int i = 1; i < index; i++) {
            current = current.link;
        }
        current.data = value;
    }

    @Override
    public void remove(Object value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                if (current.plink != null) {
                    current.plink.link = current.link;
                } else {
                    head = current.link;
                }
                if (current.link != null) {
                    current.link.plink = current.plink;
                } else {
                    tail = current.plink;
                }
                count--;
            }
            current = current.link;
        }
    }

    public boolean find(Object value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.link;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void show() {
        Node current = head;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.data);
            if(current.link != null){
                System.out.print(", ");
            }else{
                System.out.print(" ");
            }
            current = current.link;
        }
        System.out.println("]");
    }

    public void show_backward() {
        Node current = tail;
        System.out.print("[ ");
        while (current != null) {
            System.out.print(current.data);
            if(current.plink != null){
                System.out.print(", ");
            }else{
                System.out.print(" ");
            }
            current = current.plink;
        }
        System.out.println("]");
    }
    public void sort_insertion() {
        Node temp = head;
        for (int i = 0; i < count - 1; i++) {
            Node value = temp;
            for (Node tem = temp.link; tem != null; tem = tem.link) {
                if (Integer.parseInt( tem.data.toString()) < Integer.parseInt(value.data.toString())) {
                    value = tem;
                }
            }
            Object data  = temp.data;
            temp.data = value.data;
            value.data = data;

            temp = temp.link;
        }
    }
    public boolean search_sequential(Object value) {
        sort_insertion();
        for (Node temp = head;temp!=null;temp=temp.link){
            if(Integer.parseInt(temp.data.toString())==Integer.parseInt(value.toString())){
                return  true;
            }
        }
        return false;
    }

    public boolean search_binary(Object value) {
        sort_insertion();
        Node middle;
        int value_start = 0;
        int value_last = count - 1;
        int Answer = Integer.parseInt(value.toString());
        while (value_start <= value_last) {
            int value_middle = (value_start + value_last) / 2;
            middle = head;
            for (int i = 1; i < value_middle; i++) {
                middle = middle.link;
            }
            int middleValue = Integer.parseInt(middle.data.toString());

            if (middleValue == Answer) {
                return true; // พบค่า
            } else if (middleValue < Answer) {
                value_start = value_middle + 1;
            } else {
                value_last = value_middle - 1;
            }
        }
        return false;
    }
    private void update_hashtable(){
        for (Node tempMain = head;tempMain!=null;tempMain=tempMain.link){
            int index = hash(Integer.parseInt(tempMain.data.toString()));
            Object[] temp = new Object[1];
            if (hashtable[index] != null) {
                temp = new Object[((Object[]) hashtable[index]).length + 1];
            }
            int i =0;
            for(; i < temp.length - 1; i++){
                temp[i] = ((Object []) hashtable[index])[i];
            }
            temp[i] =tempMain.data;
            hashtable[index] = temp;
        }
    }

    private int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    // Check if a number is prime
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int hash(Object key) {
        return Integer.parseInt(key.toString()) % tablesize;
    }

    public boolean search_hashing(Object value) {
        sort_insertion();
        tablesize = nextPrime(count);
        hashtable = new Object[tablesize];
        update_hashtable();
        int index = hash(value);
        if (hashtable[index] != null) {
            Object[] temp = (Object[]) hashtable[index];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

}