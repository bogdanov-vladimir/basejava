import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size = 0;
    private final int MAX_COUNT = 10000;
    Resume[] storage = new Resume[MAX_COUNT];

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }

        size = 0;
    }

    void save(Resume r) {
        if (size < MAX_COUNT) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }

        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;

                for (int j = i; j < size; j++) {
                    storage[j] = storage[j + 1];
                }
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
