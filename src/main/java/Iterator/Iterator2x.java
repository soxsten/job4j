package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterator2x implements Iterator {
    private final int[][] values;
    private int i = 0;
    private int j = -1;

    Iterator2x(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {

        if (j < values[i].length - 1) {
            return true;
        }

        return i < values.length - 1;
    }

    @Override
    public Object next() throws NoSuchElementException {

        if (values.length > 0 && hasNext()) {

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
