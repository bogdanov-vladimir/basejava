package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void save(Resume r) {
        if (size < CAPACITY) {
            int index = findIndex(r.getUuid());

            if (index < 0) {
                storage[size++] = r;
            } else {
                System.out.println("Resume already exist.");
            }
        }
    }
}
