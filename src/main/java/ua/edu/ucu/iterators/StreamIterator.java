package ua.edu.ucu.iterators;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private final int[] elems;
    private int index = 0;

    public StreamIterator(int... values) {
        this.elems = values;
    }

    @Override
    public boolean hasNext() {
        return this.elems.length > this.index;
    }

    @Override
    public Integer next() {
        int nextElem = this.elems[this.index];
        this.index++;
        return nextElem;
    }
}
