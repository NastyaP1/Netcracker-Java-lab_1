package netcracker;

/**
 * класс MainView - вывод матрицы
 */
class MainView {
    /**
     * задание матриц 1 и 2
     */
    private int[][] matrix1 = {{1, 2}, {1, 2}, {1, 2}};
    private int[][] matrix2 = {{2, 2}, {2, 2}};

    /**
     * процедура вывода матрицы
     */
    void showMatrix(){
        int[][] resMatrix = CalcMatrixThread.calculate(matrix1, matrix2, 8);
        for (int[] rows : resMatrix) {
            for (int nums : rows) {
                System.out.print(nums + " ");
            }
            System.out.println();
        }
    }

}
