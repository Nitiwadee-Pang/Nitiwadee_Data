public class Heap {
    private int[] heap;
    private int size;
    private int maxSize;
    private String type ;

    public Heap(int maxSize,String type) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize];
        this.type = type;
    }
    void heapify(int arr[], int i) {
        int value = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if(type.equalsIgnoreCase("max")) {
            if (left < size && arr[left] > arr[value]) {
                value = left;
            }

            if (right < size && arr[right] > arr[value]) {
                value = right;
            }
        }
        else{
            if (left < size && arr[left] < arr[value]) {
                value = left;
            }

            if (right < size && arr[right] < arr[value]) {
                value = right;
            }
        }

        if (value != i) {
            int swap = arr[i];
            arr[i] = arr[value];
            arr[value] = swap;

            heapify(arr,  value);
        }
    }
    public void insert(int value) {
        if (size >= maxSize) {
            throw new ArrayStoreException("is Full");
        }
        heap[size] = value;
        size++;
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(heap,i);
        }
    }
    public int delete(int value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw  new RuntimeException("not found");
        }
        heap[index] = heap[size - 1];
        size--;
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(heap,i);
        }
        return value;
    }
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
