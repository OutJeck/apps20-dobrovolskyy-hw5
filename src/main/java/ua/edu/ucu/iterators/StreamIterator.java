package ua.edu.ucu.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreamIterator implements Iterator<Integer> {
    private final List<Integer> elems;
    private int index = 0;

    public StreamIterator(int... values) {
        this.elems = new ArrayList<>();
        for (int value: values) {
            this.elems.add(value);
        }
    }

    @Override
    public boolean hasNext() {
        return this.elems.size() > this.index;
    }

    @Override
    public Integer next() {
        Integer nextElem = this.elems.get(this.index);
        this.index++;
        return nextElem;
    }
}
