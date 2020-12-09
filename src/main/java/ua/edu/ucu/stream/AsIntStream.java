package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.StreamIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private final Iterator<Integer> iterator;

    private AsIntStream(int... values) {
        this.iterator = new StreamIterator(values);
    }

    private AsIntStream(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    private void isEmpty() {
        if (!this.iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        isEmpty();
        int[] values = this.toArray();
        int valSum = 0;
        for (int value: values) {
            valSum += value;
        }
        return (double) valSum / values.length;
    }

    @Override
    public Integer max() {
        isEmpty();
        int max = 0;
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (next > max) {
                max = next;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        isEmpty();
        int max = 0;
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (next < max) {
                max = next;
            }
        }
        return max;
    }

    @Override
    public long count() {
        long counter = 0;
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }
        return counter;
    }

    @Override
    public Integer sum() {
        isEmpty();
        int sumCounter = 0;
        while (iterator.hasNext()) {
            sumCounter += iterator.next();
        }
        return sumCounter;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(this.iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int adds = identity;
        while (iterator.hasNext()) {
            adds = op.apply(adds, iterator.next());
        }
        return adds;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arrList = new ArrayList<>();

        while (this.iterator.hasNext()) {
            arrList.add(this.iterator.next());
        }
        int[] outputArray = new int[arrList.size()];
        for (int i = 0; i < arrList.size(); i++) {
            outputArray[i] = arrList.get(i);
        }
        return outputArray.clone();
    }

}
