package Iterator;

import java.util.Iterator;

public class Iterator2x implements Iterator {
    private final int[][] values;
    private int size;
    private int index = -1;
    private int i = 0;
    private int j = -1;

    Iterator2x(int[][] values) {
        this.values = values;

        for (int[] value : values) {
            size += value.length;
        }
    }

    @Override
    public boolean hasNext() {
        return index < size - 1;
    }

    @Override
    public Object next() {

        index++;
        if (hasNext()) {

            if (index == 0) {
                j++;
                return values[0][0];
            }

            if (this.j + 1 < values[i].length) {
                this.j++;

            } else {
                this.j = 0;
                this.i++;
            }
        }

        return values[i][j];
    }
}
