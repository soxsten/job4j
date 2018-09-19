package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorEven implements Iterator {

    private int[] data;
    private int currentIndex = -1;
    private int nextEvenIndex = -1;

    IteratorEven(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int currentIndex = this.currentIndex;
        while (++currentIndex < data.length) {
            if (data[currentIndex] % 2 == 0) {
                this.nextEvenIndex = currentIndex;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object next() {
        if (this.nextEvenIndex != -1) {
            return getNextEven();
        }

        if (hasNext()) {
            return getNextEven();
        }
        throw new NoSuchElementException();
    }

    private int getNextEven() {
        this.currentIndex = this.nextEvenIndex;
        this.nextEvenIndex = -1;
        return data[currentIndex];
    }
}
