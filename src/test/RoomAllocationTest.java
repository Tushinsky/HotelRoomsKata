package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomAllocationTest {
    @Test
    public void exampleTests() {
        final int[][][] testCases = {
                {{1, 2}, {2, 4}, {4, 4}},         // Solution [1,2,1] or [2,1,2]
                {{1, 5}, {2, 4}, {6, 8}, {7, 7}}, // Solution [1,2,1,2], [1,2,2,1], [2,1,2,1], or [2,1,1,2
                {{15, 22}, {2, 4}, {6, 9}, {3, 33}, {12, 21}}, // Solution [1,2,2,3,2], [2,1,1,3,1], [3,1,3,2,1], etc

                {{1, 10}, {2, 5}, {6, 6}, {3, 7}, {6, 6}, {11, 13}, {9, 15}, {8, 14}},
                // Solutions include [1,2,2,3,4,1,3,2], [1,2,2,3,4,1,2,3],
                //                   [1,2,4,3,2,1,3,2], [2,3,3,1,4,2,1,3], and others

                {{8, 8}, {5, 8}, {8, 9}, {1, 4}, {1, 3}, {5, 7}, {4, 8}, {2, 2}, {4, 5}, {6, 8}}
                // Solutions include [4, 1, 5, 1, 2, 4, 2, 3, 3, 3], [5, 4, 2, 2, 1, 2, 3, 3, 1, 1], and others
        };
        assertArrayEquals(new int[]{1,2,1}, AllocatingHotelRooms.allocateRooms(new int[][]{{1, 2}, {2, 4}, {4, 4}}));
        final int[] roomsNeeded = {2, 2, 3, 4, 5};

        for (int i = 0; i < testCases.length; i++) {
            final int[][] thisTest = testCases[i];
            final Preloaded.Pair<Boolean, String> result = Preloaded.validateSolution(thisTest,
                    AllocatingHotelRooms.allocateRooms(thisTest), roomsNeeded[i]);
            assertTrue(result.getKey(), result.getValue());
        }
    }

    @Test
    public void specialCases() {

        // One customer - solution is [1]
        final int[][] specialCase1 = {{5, 100}};
        final Preloaded.Pair<Boolean, String> result1 = Preloaded.validateSolution(specialCase1, AllocatingHotelRooms.allocateRooms(specialCase1), 1);
        assertTrue(result1.getKey(), result1.getValue());

        // Non-overlapping customers, only 1 room needed. Solution is [1,1,1,1,1,1,1]
        final int[][] specialCase2 = {{15, 19}, {1, 2}, {3, 5}, {10, 10}, {6, 9}, {20, 99}, {101, 101}};
        final Preloaded.Pair<Boolean, String> result2 = Preloaded.validateSolution(specialCase2, AllocatingHotelRooms.allocateRooms(specialCase2), 1);
        assertTrue(result2.getKey(), result2.getValue());

        // All customers overlap, so each needs a new room. Solution is any permutation of [1,2,3,4,5]
        final int[][] specialCase3 = {{4, 7}, {1, 10}, {2, 5}, {3, 4}, {3, 12}};
        final Preloaded.Pair<Boolean, String> result3 = Preloaded.validateSolution(specialCase3, AllocatingHotelRooms.allocateRooms(specialCase3), 5);
        assertTrue(result3.getKey(), result3.getValue());
    }
}
