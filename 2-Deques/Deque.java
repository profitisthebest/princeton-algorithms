import java.util.Iterator;
import java.util.NoSuchElementException;

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

        Item to_return = this.first.data;
        
        if (this.size == 1) {
            this.first = null;
            this.last = null;
        }
        else {
            this.first = this.first.next;
            this.first.prev = null;
        }
        
        this.size -= 1;
        return to_return;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException("Cannot remove element from an empty deque.");
        
        Item to_return = this.last.data;

        if (this.size == 1) {
            this.first = null;
            this.last = null;
        }
        else {
            this.last = this.last.prev;
            this.last.next = null;
        }

        this.size -= 1;
        return to_return;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
    
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() { throw new UnsupportedOperationException("Remove operation is not supported."); }

        @Override
        public Item next() {
            if (hasNext() == false) { throw new NoSuchElementException("No more items in iterator."); }

            Item item = current.data;
            current = current.next;
            return item;
        }
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


        // ******************************* TEST removeFirst() **************************************
        System.out.println("\n=== Testing removeFirst() ===\n");

        // Test 1: Remove from single-item deque
        System.out.println("Test 1: Remove from single-item deque");
        Deque<String> deque11 = new Deque<>();
        deque11.addFirst("Only");
        String removed = deque11.removeFirst();
        System.out.println("Removed item: " + removed);
        System.out.println("Size after removal: " + deque11.size());
        System.out.println("Is empty? " + deque11.isEmpty());
        System.out.println("first == null? " + (deque11.first == null));
        System.out.println("last == null? " + (deque11.last == null));
        System.out.println("Expected: removed='Only', Size=0, isEmpty=true, first and last are null\n");

        // Test 2: Remove from multi-item deque
        System.out.println("Test 2: Remove from multi-item deque");
        Deque<Integer> deque12 = new Deque<>();
        deque12.addFirst(3);
        deque12.addFirst(2);
        deque12.addFirst(1);
        System.out.println("Initial size: " + deque12.size());
        int first = deque12.removeFirst();
        System.out.println("Removed first item: " + first);
        System.out.println("Size after removal: " + deque12.size());
        System.out.println("New first item: " + deque12.first.data);
        System.out.println("Expected: removed=1, Size=2, new first=2\n");

        // Test 3: Remove all items one by one
        System.out.println("Test 3: Remove all items one by one");
        Deque<String> deque13 = new Deque<>();
        deque13.addLast("A");
        deque13.addLast("B");
        deque13.addLast("C");
        System.out.println("Initial items: A, B, C");
        System.out.println("Removing: " + deque13.removeFirst());
        System.out.println("Removing: " + deque13.removeFirst());
        System.out.println("Removing: " + deque13.removeFirst());
        System.out.println("Is empty after removing all? " + deque13.isEmpty());
        System.out.println("Expected: A, B, C removed in order, isEmpty=true\n");

        // Test 4: Verify prev pointer is set to null
        System.out.println("Test 4: Verify first.prev is null after removal");
        Deque<String> deque14 = new Deque<>();
        deque14.addFirst("Second");
        deque14.addFirst("First");
        deque14.removeFirst();
        System.out.println("After removing first of two items:");
        System.out.println("first.prev == null? " + (deque14.first.prev == null));
        System.out.println("Expected: true\n");

        // Test 5: Exception on empty deque
        System.out.println("Test 5: Exception when removing from empty deque");
        Deque<String> deque15 = new Deque<>();
        try {
            deque15.removeFirst();
            System.out.println("ERROR: Should have thrown NoSuchElementException");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Correctly threw exception: " + e.getMessage());
        }
        System.out.println();

        // Test 6: Interleave add and remove operations
        System.out.println("Test 6: Interleave add and remove operations");
        Deque<Integer> deque16 = new Deque<>();
        deque16.addFirst(1);
        deque16.addFirst(2);
        System.out.println("Removed: " + deque16.removeFirst());
        deque16.addFirst(3);
        System.out.println("Removed: " + deque16.removeFirst());
        System.out.println("Final size: " + deque16.size());
        System.out.println("Final first item: " + deque16.first.data);
        System.out.println("Expected: removed 2 then 3, size=1, first=1\n");

        System.out.println("=== All removeFirst() tests complete ===");

        
        // ******************************* TEST removeLast() **************************************
        System.out.println("\n=== Testing removeLast() ===\n");

        // Test 1: Remove from single-item deque
        System.out.println("Test 1: Remove from single-item deque");
        Deque<String> deque17 = new Deque<>();
        deque17.addLast("Only");
        String removedLast = deque17.removeLast();
        System.out.println("Removed item: " + removedLast);
        System.out.println("Size after removal: " + deque17.size());
        System.out.println("Is empty? " + deque17.isEmpty());
        System.out.println("first == null? " + (deque17.first == null));
        System.out.println("last == null? " + (deque17.last == null));
        System.out.println("Expected: removed='Only', Size=0, isEmpty=true, first and last are null\n");

        // Test 2: Remove from multi-item deque
        System.out.println("Test 2: Remove from multi-item deque");
        Deque<Integer> deque18 = new Deque<>();
        deque18.addLast(1);
        deque18.addLast(2);
        deque18.addLast(3);
        System.out.println("Initial size: " + deque18.size());
        int lastItem = deque18.removeLast();
        System.out.println("Removed last item: " + lastItem);
        System.out.println("Size after removal: " + deque18.size());
        System.out.println("New last item: " + deque18.last.data);
        System.out.println("Expected: removed=3, Size=2, new last=2\n");

        // Test 3: Remove all items one by one
        System.out.println("Test 3: Remove all items one by one");
        Deque<String> deque19 = new Deque<>();
        deque19.addFirst("A");
        deque19.addFirst("B");
        deque19.addFirst("C");
        System.out.println("Initial items: C, B, A (added to front)");
        System.out.println("Removing from back: " + deque19.removeLast());
        System.out.println("Removing from back: " + deque19.removeLast());
        System.out.println("Removing from back: " + deque19.removeLast());
        System.out.println("Is empty after removing all? " + deque19.isEmpty());
        System.out.println("Expected: A, B, C removed in order, isEmpty=true\n");

        // Test 4: Verify next pointer is set to null
        System.out.println("Test 4: Verify last.next is null after removal");
        Deque<String> deque20 = new Deque<>();
        deque20.addLast("First");
        deque20.addLast("Second");
        deque20.removeLast();
        System.out.println("After removing last of two items:");
        System.out.println("last.next == null? " + (deque20.last.next == null));
        System.out.println("Expected: true\n");

        // Test 5: Exception on empty deque
        System.out.println("Test 5: Exception when removing from empty deque");
        Deque<String> deque21 = new Deque<>();
        try {
            deque21.removeLast();
            System.out.println("ERROR: Should have thrown NoSuchElementException");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Correctly threw exception: " + e.getMessage());
        }
        System.out.println();

        // Test 6: Interleave add and remove operations
        System.out.println("Test 6: Interleave add and remove operations");
        Deque<Integer> deque22 = new Deque<>();
        deque22.addLast(1);
        deque22.addLast(2);
        System.out.println("Removed from back: " + deque22.removeLast());
        deque22.addLast(3);
        System.out.println("Removed from back: " + deque22.removeLast());
        System.out.println("Final size: " + deque22.size());
        System.out.println("Final last item: " + deque22.last.data);
        System.out.println("Expected: removed 2 then 3, size=1, last=1\n");

        System.out.println("=== All removeLast() tests complete ===");


        // ******************************* TEST iterator() **************************************
        System.out.println("\n=== Testing iterator() ===\n");

        // Test 1: Iterate over empty deque
        System.out.println("Test 1: Iterate over empty deque");
        Deque<String> deque23 = new Deque<>();
        int count = 0;
        for (String item : deque23) {
            count++;
        }
        System.out.println("Number of items iterated: " + count);
        System.out.println("Expected: 0\n");

        // Test 2: Iterate over single item
        System.out.println("Test 2: Iterate over single item");
        Deque<String> deque24 = new Deque<>();
        deque24.addFirst("Only");
        System.out.print("Items: ");
        for (String item : deque24) {
            System.out.print(item + " ");
        }
        System.out.println("\nExpected: Only\n");

        // Test 3: Iterate over multiple items (added to front)
        System.out.println("Test 3: Iterate over multiple items (added to front)");
        Deque<Integer> deque25 = new Deque<>();
        deque25.addFirst(3);
        deque25.addFirst(2);
        deque25.addFirst(1);
        System.out.print("Items in order: ");
        for (int item : deque25) {
            System.out.print(item + " ");
        }
        System.out.println("\nExpected: 1 2 3\n");

        // Test 4: Iterate over multiple items (added to back)
        System.out.println("Test 4: Iterate over multiple items (added to back)");
        Deque<String> deque26 = new Deque<>();
        deque26.addLast("A");
        deque26.addLast("B");
        deque26.addLast("C");
        System.out.print("Items in order: ");
        for (String item : deque26) {
            System.out.print(item + " ");
        }
        System.out.println("\nExpected: A B C\n");

        // Test 5: Multiple iterators should be independent
        System.out.println("Test 5: Multiple independent iterators");
        Deque<Integer> deque27 = new Deque<>();
        deque27.addLast(1);
        deque27.addLast(2);
        deque27.addLast(3);
        Iterator<Integer> iter1 = deque27.iterator();
        Iterator<Integer> iter2 = deque27.iterator();
        System.out.println("First iterator first item: " + iter1.next());
        System.out.println("Second iterator first item: " + iter2.next());
        System.out.println("First iterator second item: " + iter1.next());
        System.out.println("Expected: 1, 1, 2\n");

        // Test 6: hasNext() doesn't consume items
        System.out.println("Test 6: hasNext() doesn't consume items");
        Deque<String> deque28 = new Deque<>();
        deque28.addLast("X");
        Iterator<String> iter = deque28.iterator();
        System.out.println("hasNext(): " + iter.hasNext());
        System.out.println("hasNext() again: " + iter.hasNext());
        System.out.println("next(): " + iter.next());
        System.out.println("hasNext() after consuming: " + iter.hasNext());
        System.out.println("Expected: true, true, X, false\n");

        // Test 7: NoSuchElementException when calling next() on exhausted iterator
        System.out.println("Test 7: Exception when next() called on exhausted iterator");
        Deque<String> deque29 = new Deque<>();
        deque29.addLast("Only");
        Iterator<String> iter3 = deque29.iterator();
        iter3.next(); // Consume the only item
        try {
            iter3.next(); // Should throw
            System.out.println("ERROR: Should have thrown NoSuchElementException");
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw exception: " + e.getMessage());
        }
        System.out.println();

        // Test 8: UnsupportedOperationException on remove()
        System.out.println("Test 8: Exception when remove() is called");
        Deque<Integer> deque30 = new Deque<>();
        deque30.addLast(1);
        Iterator<Integer> iter4 = deque30.iterator();
        try {
            iter4.remove();
            System.out.println("ERROR: Should have thrown UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            System.out.println("Correctly threw exception: " + e.getMessage());
        }
        System.out.println();

        // Test 9: Iterator after modifications (interleaved operations)
        System.out.println("Test 9: Iterator reflects current state of deque");
        Deque<String> deque31 = new Deque<>();
        deque31.addLast("A");
        deque31.addLast("B");
        deque31.removeFirst();
        deque31.addLast("C");
        System.out.print("Items after modifications: ");
        for (String item : deque31) {
            System.out.print(item + " ");
        }
        System.out.println("\nExpected: B C\n");

        System.out.println("=== All iterator() tests complete ===");
    }

}
