import exception.ArrayFullException;
import exception.IndexValidException;
import exception.ItemValidException;
import org.example.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {
    private final StringListImpl stringList = new StringListImpl(3);

    @BeforeEach
    public void setUp() {
        stringList.add("Привет");
        stringList.add("Как дела?");
    }

    public String arrayToString(String[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : arr) {
            stringBuilder.append(s).append(" ");
        }
        return stringBuilder.toString();
    }

    @ParameterizedTest
    @ValueSource(strings = "Пока")
    void checkAddSuccess(String item) {
        String[] expected = new String[]{"Привет", "Как дела?", "Пока"};
        stringList.add(item);
        assertEquals(arrayToString(expected), arrayToString(stringList.toArray()));
    }

    @Test
    void checkAddWithIndexSuccess() {
        int index = 1;
        String item = "Пока";
        String[] expected = new String[]{"Привет", "Пока", "Как дела?"};
        stringList.add(index, item);
        assertEquals(arrayToString(expected), arrayToString(stringList.toArray()));
    }

    @Test
    void checkSetSuccess() {
        int index = 1;
        String item = "Пока";
        String[] expected = new String[]{"Привет", "Пока"};
        stringList.set(index, item);
        assertEquals(arrayToString(expected), arrayToString(stringList.toArray()));
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    void checkRemoveSuccess(int index) {
        String[] expected = new String[]{"Привет"};
        stringList.remove(index);
        assertEquals(arrayToString(expected), arrayToString(stringList.toArray()));
    }

    @Test
    void checkContainsSuccess() {
        String expected = "Привет";
        assertTrue(stringList.contains(expected));
    }

    @Test
    void checkIndexOfSuccess() {
        String expected = "Привет";
        int actual = stringList.indexOf(expected);
        assertEquals(0, actual);
    }

    @Test
    void checkLastIndexOfSuccess() {
        String expected = "Как дела?";
        int actual = stringList.indexOf(expected);
        assertEquals(1, actual);
    }

    @Test
    void checkGet() {
        int index = 0;
        String expected = "Привет";
        String actual = stringList.get(index);
        assertEquals(expected, actual);
    }

    @Test
    void checkEquals() {
        String[] expected = new String[2];
        expected[0] = "Привет";
        expected[1] = "Как дела?";
        assertTrue(arrayToString(stringList.toArray()).equals(arrayToString(expected)));
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
        String[] expected = stringList.toArray();
        assertEquals(arrayToString(expected), arrayToString(stringList.toArray()));
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
        stringList.add("Hello");
        Exception exception = assertThrows(ArrayFullException.class,
                () -> stringList.add("By"));
        String expectedException = "Массив заполен";
        assertEquals(expectedException, exception.getMessage());
    }


}
