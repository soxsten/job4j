package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorEven implements Iterator {

    private int[] data;
    private int index = -1;

    IteratorEven(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int index = this.index;
        while (++index < data.length) {
            boolean isEven = data[index] % 2 == 0;
            if (isEven) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            while (++index < data.length) {
                boolean isEven = data[index] % 2 == 0;
                if (isEven) {
                    return data[index];
                }
            }
        }
        throw new NoSuchElementException();
    }
}
