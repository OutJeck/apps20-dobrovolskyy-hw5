package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.*;

public class AsIntStreamTest {
    private IntStream intStream;

    @Before
    public void setUp() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testAverage() {
        double result = intStream.average();
        double expResult = 1;
        assertEquals(expResult, result, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAverage() {
        AsIntStream.of().average();
    }

    @Test
    public void testMax() {
        int result = intStream.max();
        int expResult = 3;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax() {
        AsIntStream.of().max();
    }

    @Test
    public void testMin() {
        int result = intStream.min();
        int expResult = -1;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin() {
        AsIntStream.of().min();
    }

    @Test
    public void testCount() {
        long result = intStream.count();
        long expResult = 5;
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        int result = intStream.sum();
        int expResult = 5;
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum() {
        AsIntStream.of().sum();
    }

    @Test
    public void testFilter() {
        int[] result = intStream.filter(x -> x > 0).toArray();
        int[] expResult = new int[]{1, 2, 3};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testForEach() {
        String result = StreamApp.streamForEach(intStream);
        String expResult = "-10123";
        assertEquals(expResult, result);
    }

    @Test
    public void testMap() {
        int[] result = intStream.map(x -> x * x).toArray();
        int[] expResult = new int[]{1, 0, 1, 4, 9};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        int[] result = intStream.
                flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        int[] expResult = new int[]{-2, -1, 0, -1, 0, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4};
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        int result = intStream.reduce(0, (sum, x) -> sum += x);
        int expResult = 5;
        assertEquals(expResult, result);
    }

    @Test
    public void testToArray() {
        int[] result = StreamApp.streamToArray(intStream);
        int[] expResult = {-1, 0, 1, 2, 3};
        assertArrayEquals(expResult, result);
    }

}
