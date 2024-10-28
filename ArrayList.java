public class ArrayList implements Collection{
    private int capacity;
    private int count;
    private Object[] arry;

    public ArrayList(int size){
        arry = new Object[size];
        capacity = size;
        count = 0;
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

    public  Object[] sort(Object[] left,Object[] right){
        Object[] value = new Object[left.length+ right.length];
        int l=0,r = 0,index = 0;
        while (l <left.length && r < right.length){
            if(Integer.parseInt(left[l].toString()) <= Integer.parseInt(right[r].toString())){
                value[index++] = left[l++];
            }
            else{
                value[index++] = right[r++];
            }
        }
        while (l <left.length) {
            value[index++] = left[l++];
        }
        while (r < right.length) {
            value[index++] = right[r++];
        }

        return value;
    }
    public  Object[] mergesort (Object[] objects) {
        if(objects.length <=1){
            return objects;
        }
        Object[] left = new Object[objects.length/2];
        Object[] right = new Object[objects.length - left.length];
        for (int i = 0; i < left.length; i++) {
            left[i] = objects[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = objects[left.length+i];
        }
        left = mergesort(left);
        right = mergesort(right);
        return sort(left,right);
    }
    public void sort_merge() {
        arry = mergesort(arry);

    }
    public   void quickSort(Object arr[], int left, int right) {
        int i =left, j = right;
        Object tmp;
        int pivot = Integer.parseInt(arr[(left + right) / 2].toString());
        while (i <= j) {
            while (Integer.parseInt(arr[i].toString()) < pivot)
                i++;
            while (Integer.parseInt(arr[j].toString()) > pivot)
                j--;
            if (i <= j) {
                tmp = Integer.parseInt(arr[i].toString());
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };
        if (left < j)
            quickSort(arr, left, j);
        if (i < right)
            quickSort(arr, i, right);
    }
    public void sort_quick() {
        quickSort(arry,0, arry.length-1);
    }

}