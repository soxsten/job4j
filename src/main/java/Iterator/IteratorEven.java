package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorEven implements Iterator {

    private int[] data;
    private int currentIndex = -1;
    private int nextEvenIndex = -1;
    private boolean shouldUpdate;

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
            this.currentIndex = this.nextEvenIndex;
            resetNextEvenIndex();
            return data[currentIndex];
        }
        if (hasNext()) {
            resetNextEvenIndex();
            while (++currentIndex < data.length) {
                if (data[currentIndex] % 2 == 0) {
                    return data[currentIndex];
                }
            }
        }
        throw new NoSuchElementException();
    }

    private void resetNextEvenIndex() {
        this.nextEvenIndex = -1;
    }
}
