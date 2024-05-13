public class AllocatingHotelRooms {
    public static int[] allocateRooms(int[][] customers) {
        // Each customer is represented by a pair of integers, their arrival and departure days.
        // This is why the second component of the array is needed.
        return new int[customers.length];
    }
}
