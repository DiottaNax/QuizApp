package it.unibo.quiz.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteCircularIterator<X> implements Iterator<X> {

    private int current;
    private final int size;
    private final List<X> elements;
    
    public InfiniteCircularIterator(Collection<X> collection) {
        if(collection == null){
            elements = new ArrayList<>();
            this.size = 0;
        } else {
            elements = new ArrayList<>(collection);
            this.size = collection.size();
        }

        current = -1;
    }

    /**
     * Always returns true, unless the iterator is empty.
     * 
     * @return true if the iterator is not empty
     */
    @Override
    public boolean hasNext() {
        return this.size > 0;
    }

    private int calculateNext(int toAdd) {
        return this.current + toAdd >= size ? 0
            : this.current + toAdd < 0 ? size - 1 : this.current + toAdd;
    }
    
    private void controlIteratorEmptiness() {
        if (this.size <= 0) {
            throw new NoSuchElementException("Iterator is empty");
        }
    }

    @Override
    public X next() {
        this.controlIteratorEmptiness();
        this.current = calculateNext(1);
        return this.elements.get(this.current);
    }

    public X previous() {
        this.controlIteratorEmptiness();
        this.current = calculateNext(-1);
        return this.elements.get(this.current);
    }
}
