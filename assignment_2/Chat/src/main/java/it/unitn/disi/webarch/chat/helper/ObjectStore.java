package it.unitn.disi.webarch.chat.helper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public abstract class ObjectStore<T> {

    protected Set<T> store;

    public ObjectStore() {
        this.store = new HashSet<>();
    }

    protected Optional<T> get(Predicate<T> predicate) {
        Optional<T> firstHit = this.store
                .stream()
                .filter(predicate)
                .findFirst();

        return firstHit;
    }

    public boolean has(T object) {
        return this.store.contains(object);
    }

    public Set<T> getAll() {
        return this.store;
    }

    public void add(T object) {
        if (!this.has(object)) {
            this.store.add(object);
        }
    }

}
