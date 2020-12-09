package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private final IntToIntStreamFunction func;
    private final Iterator<Integer> iterator;
    private StreamIterator newIterator;

    public FlatMapIterator(Iterator<Integer> iterator, IntToIntStreamFunction func) {
        this.iterator = iterator;
        this.func = func;
        this.newIterator = new StreamIterator();
    }

    @Override
    public boolean hasNext() {
        if (newIterator.hasNext()) {
            return true;
        }
        if (iterator.hasNext()) {
            AsIntStream tempStream = (AsIntStream) func.applyAsIntStream(iterator.next());
            newIterator = new StreamIterator(tempStream.toArray());
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return newIterator.next();
    }
}
