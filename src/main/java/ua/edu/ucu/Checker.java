package ua.edu.ucu;

import ua.edu.ucu.utils.ImmutableList;

public class Checker {
    public static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException("Argument is null!");
        }
    }

    public static void checkIndex(int index, ImmutableList immutable) {
        if (index < 0 || index > immutable.size()) {
            throw new ArrayIndexOutOfBoundsException("Index is out of array!");
        }
    }

    public static void checkIndex(int index, ImmutableList immutable, boolean isAddition) {
        if (isAddition) {
            checkIndex(index, immutable);
        } else {
            if (index < 0 || index >= immutable.size()) {
                throw new ArrayIndexOutOfBoundsException("Index is out of array!");
            }
        }
    }
}
