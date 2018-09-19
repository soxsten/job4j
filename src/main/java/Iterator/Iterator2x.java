package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterator2x implements Iterator {
    private final int[][] values;
    private int index = -1;
    private int i = 0;
    private int j = -1;

    Iterator2x(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {

        int length = values[i].length - 1;
        if (j < length) {
            return true;
        }

        int length2 = values.length - 1;
        return this.i < length2;
    }

    @Override
    public Object next() throws NoSuchElementException {

        if (hasNext()) {
            index++;

            if (this.j + 1 < values[i].length) {
                this.j++;

            } else {
                this.j = 0;
                this.i++;
            }

            return values[i][j];

        } else {
            throw new NoSuchElementException();
        }
    }
}
