import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // member variables
    private Node first, last;
    private int size;

    private class Node {
        Item data;
        Node next;
        Node prev;

        Node(Item data) {
            this.data = data;
        }
    }

    // construct an empty deque
    public Deque() {
        this.first = null; 
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Method cannot accept null argument.");

        Node to_insert = new Node(item);

        if (this.isEmpty()) {
            this.first = to_insert;
            this.last = this.first;
        } 
        else {
            to_insert.next = this.first;
            this.first.prev = to_insert;
            first = to_insert;
        }
        
        this.size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Method cannot accept null argument.");

        Node to_insert = new Node(item);
        
        if (this.isEmpty()) {
            first = to_insert;
            last = first;
        }
        else {
            last.next = to_insert;
            to_insert.prev = last;
            last = to_insert;
        }

        this.size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException("Cannot remove element from an empty deque.");

        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException("Cannot remove element from an empty deque.");
        
        
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            public boolean hasNext() { return false; }
            public Item next() { throw new java.util.NoSuchElementException(); }
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {

        // ******************************* TEST addFirst() **************************************
        System.out.println("=== Testing addFirst() ===\n");

        // Test 1: Add to empty deque
        System.out.println("Test 1: Add single item to empty deque");
        Deque<String> deque1 = new Deque<>();
        deque1.addFirst("A");
        System.out.println("Size after adding 'A': " + deque1.size());
        System.out.println("Is empty? " + deque1.isEmpty());
        System.out.println("Expected: Size=1, isEmpty=false\n");

        // Test 2: Add multiple items to front
        System.out.println("Test 2: Add multiple items to front");
        Deque<Integer> deque2 = new Deque<>();
        deque2.addFirst(3);
        deque2.addFirst(2);
        deque2.addFirst(1);
        System.out.println("Added 3, 2, 1 to front");
        System.out.println("Size: " + deque2.size());
        System.out.println("Items: " + deque2.first.data + " " + deque2.first.next.data + " " + deque2.last.data);
        System.out.println("Expected: Size=3, order should be 1->2->3\n");

        // Test 3: Verify first and last pointers
        System.out.println("Test 3: Verify first and last pointers");
        Deque<String> deque3 = new Deque<>();
        deque3.addFirst("First");
        System.out.println("After adding one item:");
        System.out.println("  first == last? " + (deque3.first == deque3.last));
        deque3.addFirst("New First");
        System.out.println("After adding second item to front:");
        System.out.println("  first == last? " + (deque3.first == deque3.last));
        System.out.println("Expected: true then false\n");

        // Test 4: Verify prev/next pointers are set correctly
        System.out.println("Test 6: Verify node linking");
        Deque<String> deque6 = new Deque<>();
        deque6.addFirst("B");
        deque6.addFirst("A");
        System.out.println("Added 'B' then 'A' to front");
        System.out.println("First node's next should point to second node: " + 
                        (deque6.first.next != null));
        System.out.println("Second node's prev should point to first node: " + 
                        (deque6.first.next.prev == deque6.first));
        System.out.println("Expected: both true\n");
        
        System.out.println("=== All addFirst() tests complete ===");


        // ******************************* TEST addLast() **************************************

                
        System.out.println("\n=== Testing addLast() ===\n");

        // Test 1: Add to empty deque
        System.out.println("Test 1: Add single item to empty deque");
        Deque<String> deque7 = new Deque<>();
        deque7.addLast("A");
        System.out.println("Size after adding 'A': " + deque7.size());
        System.out.println("Is empty? " + deque7.isEmpty());
        System.out.println("Expected: Size=1, isEmpty=false\n");

        // Test 2: Add multiple items to back
        System.out.println("Test 2: Add multiple items to back");
        Deque<Integer> deque8 = new Deque<>();
        deque8.addLast(1);
        deque8.addLast(2);
        deque8.addLast(3);
        System.out.println("Added 1, 2, 3 to back");
        System.out.println("Size: " + deque8.size());
        System.out.println("Items: " + deque8.first.data + " " + deque8.first.next.data + " " + deque8.last.data);
        System.out.println("Expected: Size=3, order should be 1->2->3\n");

        // Test 3: Verify first and last pointers
        System.out.println("Test 3: Verify first and last pointers");
        Deque<String> deque9 = new Deque<>();
        deque9.addLast("First");
        System.out.println("After adding one item:");
        System.out.println("  first == last? " + (deque9.first == deque9.last));
        deque9.addLast("Second");
        System.out.println("After adding second item to back:");
        System.out.println("  first == last? " + (deque9.first == deque9.last));
        System.out.println("Expected: true then false\n");

        // Test 4: Verify prev/next pointers are set correctly
        System.out.println("Test 4: Verify node linking");
        Deque<String> deque10 = new Deque<>();
        deque10.addLast("A");
        deque10.addLast("B");
        System.out.println("Added 'A' then 'B' to back");
        System.out.println("Last node's prev should point to first node: " + 
                        (deque10.last.prev != null));
        System.out.println("First node's next should point to last node: " + 
                        (deque10.last.prev == deque10.first));
        System.out.println("Expected: both true\n");

        System.out.println("=== All addLast() tests complete ===");




    }

}
