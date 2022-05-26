package arrays;

public class ArraySimple {
    private int[] array;

    public ArraySimple(int[] array) {
        this.array = array;
    }

    public int sumArray() {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public int averageSumArray() {
        int averageSum = 0;
        for (int i = 0; i < array.length; i++) {
            averageSum += array[i];
        }
        averageSum = averageSum / array.length;
        return averageSum;
    }
}
