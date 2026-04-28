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
    public void saveTemplate(int binarySearchResume, Resume r) {
        if (size == 0) {
            storage[size++] = r;
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

        System.out.println(Arrays.toString(storage));
    }

    @Override
    public void deleteTemplate(int index) {
        System.arraycopy(storage, index + 1, storage, index, size--);
    }
}
