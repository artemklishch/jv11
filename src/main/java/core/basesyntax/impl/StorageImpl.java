package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {

    private static final int MAXIMUM_CAPACITY = 10;

    private final Pair<K, V>[] storageArray;

    private int size;

    public StorageImpl() {
        storageArray = new Pair[MAXIMUM_CAPACITY];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < storageArray.length; i++) {
            if (i == size || storageArray[i] != null && (storageArray[i].key == key
                    || storageArray[i].key != null && storageArray[i].key.equals(key))) {
                storageArray[i] = Pair.of(key, value);
                size = size == i ? size + 1 : size;
                return;
            }
        }
    }

    @Override
    public V get(K key) {
        for (Pair<K, V> pair : storageArray) {
            if (pair != null && (pair.key == key
                    || pair.key != null && pair.key.equals(key))) {
                return pair.value;
            }
        }
        return null;
    }

    private static class Pair<T, U> {
        private final T key;
        private final U value;

        private Pair(T key, U value) {
            this.key = key;
            this.value = value;
        }

        public static <T, U> Pair<T, U> of(T key, U value) {
            return new Pair<>(key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<T, U> pair = (Pair<T, U>) o;
            return (key == pair.key || (key != null && key.equals(pair.key)))
                   && (value == pair.value || (value != null && value.equals(pair.value)));
        }

        @Override
        public int hashCode() {
            int hash = key == null ? 0 : key.hashCode();
            hash = value == null ? hash : hash * 31 + value.hashCode();
            return hash;
        }
    }
}
