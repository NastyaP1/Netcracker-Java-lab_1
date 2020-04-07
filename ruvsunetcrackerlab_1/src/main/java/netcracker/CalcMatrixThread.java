package netcracker;

/**
 * класс CalcMatrixThread - перемножение матриц в потоках
 */
public class CalcMatrixThread extends Thread {
    private int startRow, endRow;
    private int[][] matrix1, matrix2, result;
    private int n;

    /**
     * @param m1 - матрица 1
     * @param m2 - матрица 2
     * @param result - результирующая матрица
     * @param startRow - начальная строка
     * @param endRow - конечная строка
     */
    private CalcMatrixThread(int[][] m1, int[][] m2, int[][] result, int startRow, int endRow) {
        this.matrix1 = m1;
        this.matrix2 = m2;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
        this.n = m2.length;
    }

    /**
     * процедура run потока по перемножению матриц
     */
    @Override
    public void run() {
        for (int row = startRow; row <= endRow ; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = calcSingleValue(row, col);
            }
        }
    }

    /**
     * процедура по вычислению значения в текущей ячейке
     * @param row - строка
     * @param col - столбец
     * @return - значение в текущей ячейке
     */
    private int calcSingleValue(int row, int col) {
        int singleValue = 0;
        for (int i = 0; i < n; i++) {
            singleValue += matrix1[row][i] * matrix2[i][col];
        }
        return singleValue;
    }


    /**
     * @param m1 - матрица 1
     * @param m2 - матрица 2
     * @param threadsCount - количество потоков
     * @return - возваращет резулютирующую матрицу после перемножения
     */
    static int[][] calculate(int[][] m1, int[][] m2, int threadsCount) {
        if (m1 == null || m1.length == 0 || m1[0] == null || m1[0].length == 0) {
            throw new IllegalArgumentException("матрица 1");
        }
        if (m2 == null || m2.length == 0 || m2[0] == null || m2[0].length == 0) {
            throw new IllegalArgumentException("матрица 2");
        }
        if (m1[0].length != m2.length) {
            throw new IllegalArgumentException("матрицы не согласованы");
        }

        int r = m1.length;
        int c = m2[0].length;
        int[][] result = new int[r][c];

        if (threadsCount > r) {
            threadsCount = r;
        }

        int count = r / threadsCount;
        int count2 = r % threadsCount;

        Thread[] threads = new Thread[threadsCount];
        int start = 0;
        for (int i = 0; i < threadsCount; i++) {
            int cnt = ((i == 0) ? count + count2 : count);
            threads[i] = new CalcMatrixThread(m1, m2, result, start, start + cnt - 1);
            start += cnt;
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        return result;
    }
}
