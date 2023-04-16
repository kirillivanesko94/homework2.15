import exception.ArrayFullException;
import exception.IndexValidException;
import exception.ItemValidException;
import org.example.StringList;
import org.example.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {
    private final StringListImpl stringList = new StringListImpl(3);

    @BeforeEach
    public void setUp() {
        stringList.add(2);
        stringList.add(1);
    }

    @ParameterizedTest
    @ValueSource(ints = 3)
    void checkAddSuccess(Integer item) {
        Integer[] expected = new Integer[]{2, 1, 3};
        stringList.add(item);
        assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void checkAddWithIndexSuccess() {
        int index = 1;
        Integer item = 3;
        Integer[] expected = new Integer[]{2, 3, 1};
        stringList.add(index, item);
        assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void checkSetSuccess() {
        int index = 1;
        Integer item = 3;
        Integer[] expected = new Integer[]{2, 3};
        stringList.set(index, item);
        assertArrayEquals(expected, stringList.toArray());
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    void checkRemoveSuccess(int index) {
        Integer[] expected = new Integer[]{2};
        stringList.remove(index);
        assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void checkContainsSuccess() {
        StringListImpl stringList = new StringListImpl(5);
        stringList.add(2);
        stringList.add(1);
        stringList.add(3);
        stringList.add(5);
        stringList.add(8);
        assertTrue(stringList.contains(3));
    }

    @Test
    void checkIndexOfSuccess() {
        Integer expected = 2;
        int actual = stringList.indexOf(expected);
        assertEquals(0, actual);
    }

    @Test
    void checkLastIndexOfSuccess() {
        Integer expected = 1;
        int actual = stringList.indexOf(expected);
        assertEquals(1, actual);
    }

    @Test
    void checkGet() {
        int index = 0;
        Integer expected = 2;
        Integer actual = stringList.get(index);
        assertEquals(expected, actual);
    }

    @Test
    void checkEquals() {
        StringList stringList1 = new StringListImpl(3);
        stringList1.add(1);
        stringList1.add(2);
        StringList stringList2 = new StringListImpl(3);
        stringList2.add(1);
        stringList2.add(2);
        assertTrue(stringList1.equals(stringList2));

    }

    @Test
    void checkSize() {
        assertEquals(2, stringList.size());
    }

    @Test
    void checkIsEmpty() {
        assertFalse(stringList.isEmpty());
    }

    @Test
    void checkClear() {
        stringList.clear();
        assertNull(stringList.get(0));
        assertNull(stringList.get(1));
    }

    @Test
    void checkToArray() {
        Integer[] expected = stringList.toArray();
        assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void checkValidateItem() {
        Exception exception = assertThrows(ItemValidException.class,
                () -> stringList.validateItem(null));
        String expectedException = "Указан неверный элемент";
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void checkValidateIndex() {
        Exception exception = assertThrows(IndexValidException.class,
                () -> stringList.validateIndex(1000000));
        String expectedException = "Указан неверный индекс";
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void checkValidateSize() {
        stringList.add(5);
        Exception exception = assertThrows(ArrayFullException.class,
                () -> stringList.add(6));
        String expectedException = "Массив заполен";
        assertEquals(expectedException, exception.getMessage());
    }



}
