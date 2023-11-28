package ch.zhaw.ads;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */
public class ADS11_3_test {
    final int DATAELEMS = 10000;
    SortServer sortServer = new SortServer();

    @Test
    public void testRandomData() {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        assertEquals(sortServer.dataElems, data.length, "Anzahl Daten");
        assertTrue(data[0] != data[1], "Distribution");
    }

    @Test
    public void testAscendingData() {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.ascendingData();
        assertEquals(sortServer.dataElems, data.length, "Anzahl Daten");
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue(data[i] <= data[i + 1], "Distribution");
        }
    }

    @Test
    public void testDescendingData() {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.descendingData();
        assertEquals(sortServer.dataElems, data.length, "Anzahl Daten");
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue(data[i] >= data[i + 1], "Distribution");
        }
    }

    @Test
    public void testBubbleSort() {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        sortServer.bubbleSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue(data[i] <= data[i + 1], "Sorted");
        }
    }

    @Test
    public void testInsertionSort() {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        sortServer.insertionSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue(data[i] <= data[i + 1], "Sorted");
        }
    }

    @Test
    public void testSelectionSort() {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        sortServer.selectionSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue(data[i] <= data[i + 1], "Sorted");
        }
    }

    private double testQuadratic(String msg, Consumer<int[]> sorter) throws Exception {
        sortServer.dataElems = DATAELEMS;
        double time1 = sortServer.measureTime(sortServer::randomData, sorter);

        sortServer.dataElems = DATAELEMS*2;
        double time2 = sortServer.measureTime(sortServer::randomData, sorter);

        assertTrue(time1 > time2 / 6 && time1 < time2 / 2, msg + " Time O(n^2)");
        return time1;
    }

    @Test
    public void testMeasureTime() throws Exception {
        double time1 = testQuadratic("BUBBLESORT", sortServer::bubbleSort);
        double time2 = testQuadratic("SELECTIONSORT", sortServer::selectionSort);
        double time3 = testQuadratic("INSERTIONSORT", sortServer::insertionSort);
        assertTrue(time1 > 1.5 * time2, "BubbleTime > 1.5 * SelectionTime");
        assertTrue(time2 > 1.5 * time3, "SelectionTime > 1.5 * InsertionTime");
    }
}
