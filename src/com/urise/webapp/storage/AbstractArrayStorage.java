package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int CAPACITY = 10_000;
    Resume[] storage = new Resume[CAPACITY];
    protected int size = 0;

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume not found.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);

        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Resume not found.");

        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);

        if (index >= 0) {
            storage[index] = storage[--size];
            storage[size] = null;
        } else {
            System.out.println("Resume not found.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public abstract void save(Resume r);

    protected abstract int findIndex(String uuid);
}
