package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void save(Resume r) {
        if (size < CAPACITY) {
            if (size == 0) {
                storage[size++] = r;
                return;
            }

            int binarySearchResume = Arrays.binarySearch(storage, 0, size, r);

            if (binarySearchResume > 0) {
                System.out.println("Resume already exist.");
            } else {
                int calculateIndex = -(binarySearchResume + 1);

                if (calculateIndex == size) {
                    storage[calculateIndex] = r;
                    size++;
                    System.out.println(Arrays.toString(storage));
                    return;
                }

                System.arraycopy(storage, calculateIndex, storage, calculateIndex + 1, size + 1);
                storage[calculateIndex] = r;
                size++;
            }
        }
    }
}
