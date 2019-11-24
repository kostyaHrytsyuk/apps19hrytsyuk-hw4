package ua.edu.ucu.utils;

import java.util.Iterator;

public class Queue {
    private ImmutableLinkedList linkedList;

    public Queue() {
        this.linkedList = new ImmutableLinkedList();
    }

    public Queue(Object[] o) {
        this.linkedList = new ImmutableLinkedList().addAll(o);
    }

    public Object peek() {
        return linkedList.getFirst();
    }

    public Object dequeue() {
        Object head = peek();

        this.linkedList = this.linkedList.removeFirst();

        return head;
    }

    public void enqueue(Object e) {
        this.linkedList = this.linkedList.add(e);
    }

    public int size() {
        if (this.linkedList != null) {
            return this.linkedList.size();
        } else {
            return 0;
        }
    }

    public Iterable<String> toArray(){
        return getIterator(this);
    }

    private Iterable<String> getIterator(Queue queue) {
        return new Iterable<String>() {
            @Override
            public QueueIterator iterator() {
                return new QueueIterator(queue);
            }
        };
    }

//    public Iterator<String> iterator() {
//        return new QueueIterator(this);
//    }
//
    private final class QueueIterator implements Iterator<String> {

        private final Queue queue;

        public QueueIterator(Queue queue) {
            this.queue = queue;
        }

        @Override
        public boolean hasNext() {
            return this.queue.size() != 0;
        }

        @Override
        public String next() {
            return (String) this.queue.dequeue();
        }
    }
}
