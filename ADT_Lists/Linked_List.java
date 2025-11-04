package ADT_Lists;

import ADT_Nodes.*;

public abstract class Linked_List<T extends Comparable<T>> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int size;

    // Enum the sort type being compared to
    public enum Sort_Type {
        BUBBLE,
        MERGE,
    }

    public Linked_List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Can be append or push
    public abstract void add(T data);

    // Remove by first occurence
    public abstract boolean remove(T data);

    // Find first equal
    public abstract Node<T> find(T data);

    public int get_size() {
        return this.size;
    }

    public boolean is_empty() {
        return this.size == 0;
    }

    // Iterate and apply a visitor lambda
    public void for_each(Visitor<T> visitor) {
        Node<T> current = this.head;
        int iter_count = 0;
        while (current != null && iter_count < this.size) {
            visitor.visit(current.get_data());
            current = current.get_next();
            iter_count++;
        }
    }

    public Node<T> get_head() {
        return this.head;
    }

    public interface Visitor<T> {
        void visit(T item);
    }

    public Node<T> find_object(T data) {
        Node<T> current = this.head;
        while (current != null) {
            if (data.compareTo(current.get_data()) == 0) {
                return current;
            }
            current = current.get_next();
        }
        return null;
    }

    public Node<T> node_at(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + this.size
            );
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.get_next();
        }
        return current;
    }

    public void sort(Sort_Type type) {
        switch (type) {
            case BUBBLE:
                bubble_sort();
                break;
            case MERGE:
                this.head = merge_sort(this.head);
                // after merge, recompute tail
                recompute_tail();
                break;
            default:
                // default to merge O(n log n)
                this.head = merge_sort(this.head);
                recompute_tail();
                break;
        }
    }
    private void bubble_sort() {
        if (this.head == null || this.size <= 1) {
            return; // Already sorted or empty
        }
        
        boolean swapped;
        Node<T> current;
        Node<T> last = null; // Marks the end of sorted portion
        
        do {
            swapped = false;
            current = this.head;
            
            while (current.get_next() != last) {
                Node<T> next = current.get_next();
                
                // Compare current with next node
                if (current.get_data().compareTo(next.get_data()) > 0) {
                    // Swap data between nodes
                    T temp = current.get_data();
                    current.set_data(next.get_data());
                    next.set_data(temp);
                    swapped = true;
                }
                
                current = current.get_next();
            }
            
            // After each pass, the largest element is at the end
            last = current;
            
        } while (swapped);
    }

    private Node<T> merge_sort(Node<T> node_head) {
        if (node_head == null || node_head.get_next() == null) return node_head;

        Node<T> middle = get_middle(node_head);
        Node<T> next_of_mid = middle.get_next();
        // break list
        middle.set_next(null);

        Node<T> left = merge_sort(node_head);
        Node<T> right = merge_sort(next_of_mid);

        return sorted_merge(left, right);
    }

    private Node<T> sorted_merge(Node<T> a, Node<T> b) {
        if (a == null) return b;
        if (b == null) return a;

        Node<T> result;
        if (a.get_data().compareTo(b.get_data()) <= 0) {
            result = a;
            result.set_next(sorted_merge(a.get_next(), b));
        } else {
            result = b;
            result.set_next(sorted_merge(a, b.get_next()));
        }
        return result;
    }

    private Node<T> get_middle(Node<T> head) {
        if (head == null) return head;
        Node<T> slow = head;
        Node<T> fast = head.get_next();
        while (fast != null) {
            fast = fast.get_next();
            if (fast != null) {
                slow = slow.get_next();
                fast = fast.get_next();
            }
        }
        return slow;
    }

    private void recompute_tail() {
        Node<T> cur = this.head;
        Node<T> last = null;
        int counter = 0;
        while (cur != null) {
            last = cur;
            cur = cur.get_next();
            counter++;
        }
        this.tail = last;
        this.size = counter;
    }

    public boolean is_sorted() {
        if (this.head == null) return true;
        Node<T> cur = this.head;
        while (cur != null && cur.get_next() != null) {
            if (
                cur.get_data().compareTo(cur.get_next().get_data()) > 0
            ) return false;
            cur = cur.get_next();
        }
        return true;
    }
}
