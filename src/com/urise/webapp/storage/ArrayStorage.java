package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int CAPACITY = 10000;
    Resume[] storage = new Resume[CAPACITY];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < CAPACITY) {
            int idxSearch = searchIndexFromUUID(r.getUuid(), false);

            if (idxSearch < 0) {
                storage[size++] = r;
            }
        }
    }

    public Resume get(String uuid) {
        int idxSearch = searchIndexFromUUID(uuid, true);

        if (idxSearch >= 0) {
            return storage[idxSearch];
        }

        return null;
    }

    public void update(String oldUuid, String newUuid) {
        int idxSearch = searchIndexFromUUID(oldUuid, true);

        if (idxSearch >= 0) {
            storage[idxSearch] = new Resume(newUuid);
        }
    }

    public void delete(String uuid) {
        int idxSearch = searchIndexFromUUID(uuid, true);

        if (idxSearch >= 0) {
            storage[idxSearch] = storage[--size];
            storage[size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int searchIndexFromUUID(String uuid, boolean printFound) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }

        if (printFound) {
            System.out.println("Resume not found.");
        }

        return -1;
    }
}
