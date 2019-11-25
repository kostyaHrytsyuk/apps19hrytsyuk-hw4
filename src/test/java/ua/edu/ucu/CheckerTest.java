package ua.edu.ucu;

import org.junit.Test;
import ua.edu.ucu.utils.ImmutableLinkedList;
import ua.edu.ucu.utils.Queue;

import static org.junit.Assert.*;

public class CheckerTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void checkIndex() {
        Checker.checkIndex(-5, new ImmutableLinkedList(new Object[] {"hi!"}), true);
    }
}