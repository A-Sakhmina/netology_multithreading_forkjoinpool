package arrays;

import java.util.concurrent.RecursiveTask;

public class ArrayAverageSumTask extends RecursiveTask<Integer> {
    private int start;
    private int end;
    private int array[];

    public ArrayAverageSumTask(int start, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        switch (diff) {
            case 0:
                return 0;
            case 1:
                return array[start];
            case 2:
                return (array[start] + array[start + 1]) / diff;
            case 3:
                return (array[start] + array[start + 1] + array[start + 2]) / diff;
            case 4:
                return (array[start] + array[start + 1] + array[start + 2] + array[start + 3]) / diff;
            default:
                return forkTasksAndGetResult();
        }
    }

    private int forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        // Создаем задачу для левой части диапазона
        ArrayAverageSumTask task1 = new ArrayAverageSumTask(start, middle, array);
        // Создаем задачу для правой части диапазона
        ArrayAverageSumTask task2 = new ArrayAverageSumTask(middle, end, array);
        // Запускаем обе задачи в пуле
        invokeAll(task1, task2);
        // Суммируем результаты выполнения обоих задач
        return (task1.join() + task2.join()) / 2;
    }
}

