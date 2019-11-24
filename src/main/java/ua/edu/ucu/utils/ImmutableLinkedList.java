package ua.edu.ucu.utils;

import ua.edu.ucu.Checker;

public final class ImmutableLinkedList implements ImmutableList {
    private Node listHead;
    private Node listTail;
    private int length;

    public ImmutableLinkedList() { }

    public ImmutableLinkedList(Object[] arr) {
        ImmutableLinkedList l = addAll(arr);
        this.listHead = l.listHead;
        this.listTail = l.listTail;
        this.length = l.length;
    }

    private ImmutableLinkedList(Node head, Node tail, int length) {
        if (head != null) {
            this.listHead = head.copyOf();
            if (tail != null) {
                Node parent = this.listHead;
                for (int i = 0; i < length-1; i++) {
                    Node current = parent.getTail().copyOf();
                    setRelations(parent, current);
                    parent = current;
                }
                this.listTail = parent;
            }
        }
        this.length = length;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(this.length, e);    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        Checker.checkNull(e);
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.length, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        Checker.checkIndex(index, this);
        Checker.checkNull(c);
        ImmutableLinkedList imList = new ImmutableLinkedList(this.listHead, this.listTail, this.length);
        Node node = new Node(c[0]);
        if (imList.isEmpty()) {
            imList.listHead = node;
            Node curr = imList.listHead;
            Node tempNode;
            for (int i = 1; i < c.length; i++) {
                tempNode = new Node(c[i]);
                setRelations(curr, tempNode);
                curr = tempNode;
            }
            imList.listTail = curr;
        } else if (imList.length == 1) {
            setRelations(imList.listHead, node);
            imList.listTail = node;
            Node curr = node;
            Node tempNode;
            for (int i = 1; i < c.length; i++) {
                tempNode = new Node(c[i]);
                setRelations(curr, tempNode);
                curr = tempNode;
            }
            imList.listTail = curr;
        } else if (imList.length == index) {
            Node curr = imList.getNode(index-1);
            Node tempNode;
            for (int i = 0; i < c.length; i++) {
                tempNode = new Node(c[i]);
                setRelations(curr, tempNode);
                curr = tempNode;
            }
            imList.listTail = curr;
        } else {
            Node nodeTail = imList.getNode(index);
            setRelations(imList.getNode(index-1), node);
            Node curr = imList.getNode(index);
            Node tempNode;
            for (int i = 1; i < c.length; i++) {
                tempNode = new Node(c[i]);
                setRelations(curr, tempNode);
                curr = tempNode;
            }
            curr.setTail(nodeTail);
            imList.listTail = nodeTail;
        }
        imList.length += c.length;
        return imList;
    }

    public ImmutableLinkedList addFirst(Object e) {
        Checker.checkNull(e);
        Node node = new Node(e);
        ImmutableLinkedList imList =
                new ImmutableLinkedList(this.listHead, this.listTail, this.length);

        if (imList.isEmpty()) {
            return add(e);
        }
        else {
            setRelations(node, imList.listHead);
            imList.listHead = node;
        }
        imList.length++;
        return imList;
    }

    public ImmutableLinkedList addLast(Object e) {
        Checker.checkNull(e);
        Node node = new Node(e);
        ImmutableLinkedList imList =
                new ImmutableLinkedList(this.listHead, this.listTail, this.length);

        if (imList.isEmpty()) {
            return add(e);
        } else {
            setRelations(imList.listHead, node);
            imList.listTail = node;
        }

        imList.length++;
        return imList;
    }

    public Object getFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return this.listHead.getVal();
        }
    }

    public Object getLast() {
        if (isEmpty()) {
            return null;
        } else {
            return this.listTail.getVal();
        }
    }

    public ImmutableLinkedList removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            ImmutableLinkedList imList =
                    new ImmutableLinkedList(this.listHead, this.listTail, this.length);
            imList.listHead = imList.listHead.getTail();
            imList.length--;
            return imList;
        }
    }

    public ImmutableLinkedList removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            ImmutableLinkedList imList =
                    new ImmutableLinkedList(this.listHead, this.listTail, this.length);
            imList.listTail = imList.listTail.getHead();
            imList.length--;
            return imList;
        }
    }

    @Override
    public Object get(int index) {
        Checker.checkIndex(index, this, false);

        Node node = getNode(index);

        return node.getVal();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        Checker.checkIndex(index, this, false);
        ImmutableLinkedList imList =
                new ImmutableLinkedList(this.listHead, this.listTail, this.length);
        Node exNode = imList.getNode(index);
        Node exNodeParent = exNode.getHead();
        exNodeParent.setTail(exNode.getTail());
        imList.length--;
        return imList;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        Checker.checkIndex(index, this, false);
        Checker.checkNull(e);
        ImmutableLinkedList imList =
                new ImmutableLinkedList(this.listHead, this.listTail, this.length);
        imList.getNode(index).setVal(e);
        return imList;
    }

    @Override
    public int indexOf(Object e) {
        Checker.checkNull(e);
        int i = 0;
        Node current = this.listHead;
        while (i < this.length) {
            if (current.getVal().equals(e)) {
                return i;
            }
            current = current.getTail();
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.listHead == null;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.length];
        Node curr = this.listHead;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = curr.getVal();
            curr = curr.getTail();
        }

        return arr;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        } else {
            Node curr = this.listHead;
            StringBuilder s = new StringBuilder(String.format("%s", curr.getVal()));
            if (this.length == 1) {
                return s.toString();
            }
            curr = curr.getTail();
            while (curr != null) {
                s.append(String.format(" ,%s", curr.getVal()));
                curr = curr.getTail();
            }
            return s.toString();
        }
    }

    private Node getNode(int index) {
        Node tempNode = this.listHead;
        int counter = 0;
        while (counter != index) {
            tempNode = tempNode.getTail();
            counter++;
        }
        return tempNode;
    }

    private void setRelations(Node headNode, Node tailNode) {
        headNode.setTail(tailNode);
        tailNode.setHead(headNode);
    }

    private static class Node {
        private Node head;
        private Node tail;
        private Object val;

        public Node() { }

        Node(Object val) {
            this.val = val;
        }

        private Node(Node head, Node tail, Object val) {
            this.head = head;
            this.tail = tail;
            this.val = val;
        }

        Node getHead() {
            return head;
        }

        void setHead(Node head) {
            this.head = head;
        }

        Node getTail() {
            return tail;
        }

        void setTail(Node tail) {
            this.tail = tail;
        }

        Object getVal() {
            return val;
        }

        void setVal(Object val) {
            this.val = val;
        }

        Node copyOf() {
            return new Node(this.head, this.tail, this.val);
        }
    }


}
