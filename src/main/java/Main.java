import arrays.ArrayAverageSumTask;
import arrays.ArraySimple;
import arrays.ArraySumTask;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        int arraySize = 50_000_000;
        int[] arr = generateArray(arraySize);

        //создаём задачи, которые будут решаться через RecursiveTask<Integer>
        ArraySumTask arraySum = new ArraySumTask(0, arraySize, arr);
        ArrayAverageSumTask arrayAverageSum = new ArrayAverageSumTask(0, arraySize, arr);
        //создаём объект для не многопоточного решения
        ArraySimple arraySimple = new ArraySimple(arr);

        long startTimeFork = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //запускаем задачи в пул
        System.out.println(forkJoinPool.invoke(arraySum));
        System.out.println(forkJoinPool.invoke(arrayAverageSum));

        long endTimeFork = System.currentTimeMillis();
        System.out.println("total for fork =" + (endTimeFork - startTimeFork));

        //обычное решение
        System.out.println(arraySimple.sumArray());
        System.out.println(arraySimple.averageSumArray());

        long endTimeSimple = System.currentTimeMillis();
        System.out.println("total for simple =" + (endTimeSimple - endTimeFork));

    }

    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 3;
        }
        return arr;
    }
}
