/**
 * Array Heap implementation of a priority queue
 * @author Lachlan Plant
 */
public class HeapPriorityQueue<K extends Comparable,V> implements PriorityQueue<K ,V> {

    private Entry[] storage; //The Heap itself in array form
    private int tail;        //Index of last element in the heap

    /**
     * Default constructor
     */
    public HeapPriorityQueue() {
        this.storage = new Entry[26];
        this.tail = 0;
    }

    /**
     * HeapPriorityQueue constructor with max storage of size elements
     */
    public HeapPriorityQueue(int size) {
        this.storage = new Entry[size];
        this.tail = 0;
    }

    /****************************************************
     *
     *             Priority Queue Methods
     *
     ****************************************************/

    /**
     * Returns the number of items in the priority queue.
     * O(1)
     *
     * @return number of items
     */
    public int size() {
        return this.tail;
    }

    /**
     * Tests whether the priority queue is empty.
     * O(1)
     *
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.tail == 0;
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     * O(log(n))
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the heap is full
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        if (tail == storage.length) {
            throw new IllegalArgumentException();
        }
        Entry entry = new Entry<>(key, value);
        storage[tail] = entry;
        tail++;
        upHeap(tail-1);

        return entry;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     * O(1)
     *
     * @return entry having a minimal key (or null if empty)
     */
    public Entry<K, V> min() {
        if (isEmpty()) {
            return null;
        }
        return storage[0];
    }
        /**
         * Removes and returns an entry with minimal key.
         * O(log(n))
         * @return the removed entry (or null if empty)
         */
        public Entry<K, V> removeMin () {
            if(isEmpty()){
                return null;
            }
            Entry min = storage[0];
            swap(0,tail-1);
            storage[tail-1] = null;
            tail--;
            downHeap(0);

            return min;
        }


        /****************************************************
         *
         *           Methods for Heap Operations
         *
         ****************************************************/

        /**
         * Algorithm to place element after insertion at the tail.
         * O(log(n))
         */
        private void upHeap ( int location){
            while(location>0){
                int parent = parent(location);
                // Checks if current node is larger than it's parent and if it is the program breaks out of the loop
                if(storage[location].getKey().compareTo(storage[parent].getKey())>=0){
                    break;
                }
                swap(location,parent);
                location = parent;
            }
        }

        /**
         * Algorithm to place element after removal of root and tail element placed at root.
         * O(log(n))
         */
        private void downHeap ( int location){
            while(2*location+1<size()){
                int leftChild = 2*location+1;
                int smallestChild = leftChild;

                // Checks if there is a right child;
                if(2*location+2<size()) {
                    int rightChild = 2 * location + 2;
                    // Checks which child node is smaller
                    if (storage[leftChild].getKey().compareTo(storage[rightChild].getKey()) > 0) {
                        smallestChild = rightChild;
                    }
                }
                /*
                Checks if the smallest child is bigger than the parent. If the child is bigger than the parent
                the program breaks out of the loop since the min heap condition is satisfied
                 */
                if(storage[smallestChild].getKey().compareTo(storage[location].getKey())>=0){
                    break;
                }
                // Swaps nodes if smaller child is smaller than the parent node
                swap(location,smallestChild);
                location = smallestChild;

            }
        }


        /**
         * Find parent of a given location,
         * Parent of the root is the root
         * O(1)
         */
        private int parent ( int location){
            // Returns if parent is the root
            if(location < 1){
                return 0;
            }
            return location/2;
        }


        /**
         * Inplace swap of 2 elements, assumes locations are in array
         * O(1)
         */
        private void swap ( int location1, int location2){
            Entry  temp = storage[location1];
            storage[location1] = storage[location2];
            storage[location2] = temp;
        }

    }

