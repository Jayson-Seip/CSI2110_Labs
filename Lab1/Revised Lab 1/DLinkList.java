public class DLinkList {
    DNode head;
    DNode tail;
    long size;

    DLinkList(long sz) {
        // Note the size of the linked list will not count the dummy head or tail
        this.head = new DNode("dummy_head", null, null);
        this.tail = new DNode("dummy_tail", null, null);
        this.head.setNext(this.tail);

        if (sz != 0) {
            DNode current = head;
            for (int i=0; i<sz; i++) {
                DNode next_node = new DNode(Integer.toString(i), current, this.tail);
                this.tail.setPrev(next_node);
                current.setNext(next_node);
                current = next_node;
            }
        }
        this.size = sz;
    }

    public void print() {
        // Case if list is empty
        if(size == 0){
            System.out.println("List is empty");
            return;
        }

        DNode current = head;
       while(current.getNext() != null) {
            System.out.println(current.getElement());
            current = current.getNext();
        }
    }

    public void deleteFirst() {
        // Checks if list is empty
        if(this.head.getNext() == null){
            System.out.println("List is empty");
            return;
        }


        DNode current = head.getNext().getNext(); // sets node to the
        // Changes the pointer of the list
        this.head.setNext(current);
        current.setPrev(this.head);
        size--;

    }

    public void deleteLast() {

        // Case if list is empty
        if(this.tail.getPrev() == null){
            System.out.println("List is empty");
            return;
        }
        DNode current = this.tail.getPrev().getPrev(); // Sets current node the penultimate node
        this.tail.setPrev(current);// Changes previous tail pointer
        current.setNext(this.tail); // Sets penultimate node to dummy tail
        size--;
    }

    public static void main(String[] args) {
        long list_size = 100000;
        DLinkList llist = new DLinkList(list_size);
        llist.print();

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < list_size; i++) {
            llist.deleteFirst();
        }
        long t2 = System.currentTimeMillis();
        double total_time_in_secs = (t2-t1)/(1000.0);
        System.out.println("The total running time in seconds is " + total_time_in_secs);
        llist.print();
    }
}
