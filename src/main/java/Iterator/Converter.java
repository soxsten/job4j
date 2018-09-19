package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Converter {

    private Iterator<Iterator<Integer>> iterators;
    private Iterator<Integer> currentIterator;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.iterators = it;
        setCurrentIterator();

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {

                if (currentIterator == null) {
                    return false;
                }

                if (!currentIterator.hasNext()) {
                    if (!setCurrentIterator()) {
                        return false;
                    }
                }

                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return currentIterator.next();

                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    private boolean setCurrentIterator() {
        if (iterators.hasNext()) {
            boolean repeat = true;

            do {
                this.currentIterator = iterators.next();
                if (this.currentIterator.hasNext()) {
                    repeat = false;
                }
            } while (repeat && iterators.hasNext());
            return true;

        } else {
            this.currentIterator = null;
            return false;
        }
    }
}
