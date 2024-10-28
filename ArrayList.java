public class ArrayList implements Collection{
    private int capacity;
    private int count;
    private Object[] arry;
    Object [] hashtable;
    int tablesize = 2;
    public ArrayList(int size){
        arry = new Object[size];
        capacity = size;
        count = 0;

        tablesize = nextPrime(size);
        hashtable = new Object[tablesize];
    }

    @Override
    public void add(Object value) {
        if(!isFull()){
            add(count, value);
        }else {
            throw new RuntimeException("is full");
        }
    }

    @Override
    public void add(int index, Object value) {
        if(index == count) {
            arry[index] = value;
        } else if (index < count) {
            for(int i = count; i > index; i--){
                arry[i] = arry[i-1];
            }
            arry[index] = value;
        } else {
            throw new RuntimeException("out of bounds");
        }
        update_hashtable();
        count++;
    }

    @Override
    public Object get(int index) {
        if(index < count) {
            return arry[index];
        } else{
            throw new RuntimeException("not found");
        }
    }

    @Override
    public void set(int index, Object value) {
        if(index < count){
            arry[index] = value;
        }else{
            throw new RuntimeException("not found");
        }
    }

    @Override
    public void remove(Object value) {
        if(indexOf(value) == -1){
            throw new RuntimeException("not found");
        }
        Object[] temp_arry = new Object[capacity];
        int temp_count = 0;
        for(int i=0; i < capacity; i++){
            if(arry[i] != value){
                temp_arry[temp_count++] = arry[i];
            } else {
                count--;
            }
        }
        arry = temp_arry;
    }

    public int indexOf(Object value) {
        for(int i=0;i<count;i++){
            if(arry[i]== value) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return count;
    }

    public int max_size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }


    public boolean isFull() {
        return count >= capacity;
    }

    @Override
    public void show() {
        System.out.print("[");
        if(!isEmpty())
            for(int i=0;i<max_size();i++){
                if(i == 0){
                    System.out.print(" ");
                }
                System.out.print(i);
                System.out.print("=>");
                System.out.print(arry[i]);
                if(i < max_size()-1){
                    System.out.print(", ");
                }else {
                    System.out.print(" ");
                }
            }
        System.out.println("]");
    }
    public void sort_selection() {
        for (int i = 0; i < arry.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arry.length; j++) {
                if(Integer.parseInt(arry[min].toString()) > Integer.parseInt(arry[j].toString())){
                    min = j;
                }
            }
            Object temp = arry[i];
            arry[i] = arry[min];
            arry[min] = temp;
        }
    }
    public boolean search_sequential(Object value) {
        sort_selection();
        for (int i = 0; i < size(); i++) {
            if(Integer.parseInt(arry[i].toString()) == Integer.parseInt(value.toString())){
                return  true;
            }
        }
        return false;
    }

    public boolean search_binary(Object value) {
        sort_selection();
        int value_start = 0;
        int value_last = count - 1;
        int Answer = Integer.parseInt(value.toString());
        while (value_start <= value_last) {
            int value_middle = (value_start + value_last) / 2;
            int middleValue = Integer.parseInt(arry[value_middle].toString());
            if (middleValue == Answer) {
                return true;
            } else if (middleValue < Answer) {
                value_start = value_middle + 1;
            } else {
                value_last = value_middle - 1;
            }
        }
        return false;
    }
    private void update_hashtable(){
        int index = hash(arry[count]);
        Object[] temp = new Object[1];
        if (hashtable[index] != null) {
            temp = new Object[((Object[]) hashtable[index]).length + 1];
        }
        int i =0;
        for(; i < temp.length - 1; i++){
            temp[i] = ((Object []) hashtable[index])[i];
        }
        temp[i] = arry[count];
        hashtable[index] = temp;
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