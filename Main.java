public static void main(String[] args) {
    DoubleLinkedList list = new DoubleLinkedList();
    list.add("banana");
    list.add("apple");
    list.add("orange");
    list.add("grape");
    list.add("mango");

    System.out.println("Before Sorting:");
    list.show();

    list.sort_selection(); // หรือใช้ list.sort_insertion();
    System.out.println("After Sorting (Selection Sort):");
    list.show();
}
