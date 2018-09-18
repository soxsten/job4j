package Iterator;

import org.junit.Assert;
import org.junit.Test;

public class Iterator2xTest {

    @Test
    public void next_should_return_one() {
        //given
        int expected = 1;

        //when
        Iterator2x iterator = new Iterator2x(get2xMatrix());
        int actual = (int) iterator.next();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void next_should_return_four() {
        //given
        int expected = 4;

        //when
        Iterator2x iterator = new Iterator2x(get2xMatrix());
        int next1 = (int) iterator.next();
        int next2 = (int) iterator.next();
        int next3 = (int) iterator.next();
        int actual = (int) iterator.next();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void next_should_return_false() {

        //when
        Iterator2x iterator = new Iterator2x(get2xMatrix());
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        int next = (int) iterator.next();

        //then
        Assert.assertFalse(iterator.hasNext());
    }

    private int[][] get2xMatrix() {
        int matrixA[][] = new int[2][3];

        matrixA[0][0] = 1;
        matrixA[0][1] = -2;
        matrixA[0][2] = 3;
        matrixA[1][0] = 4;
        matrixA[1][1] = 1;
        matrixA[1][2] = 7;

        return matrixA;
    }
}
