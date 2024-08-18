import java.util.Arrays;

public class Matrices {
    static <T> T todo() {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    /**
     * Вектор длины n
     */
    public static class Vector {
        private final int[] items;

        /**
         * Создает нулевой вектор длины n
         */
        public Vector(int n) {
            this.items = new int[n];
        }

        /**
         * Создает вектор c заданными элементами.
         */
        public Vector(int[] items) {
            this.items = items;
        }

        /**
         * Складывает данный вектор с другим вектором.
         *
         * @param other вектор, который должен быть сложен с данным вектором; должен
         *              иметь ту же длину, что и данный вектор
         * @return новый вектор, представляющий сумму данного и другого векторов
         */
        public Vector add(Vector other) {
            if(items.length != other.items.length) {
                throw new IllegalArgumentException("Для сложения векторов они должны быть одной длины");
            }
            int [] vecSumm = new int[items.length];
            for(int i = 0; i < items.length; i++) {
                vecSumm[i] = items[i] + other.items[i];
            }
            return new Vector(vecSumm);

        }
        /**
         * Вычитает другой вектор из данного вектора.
         *
         * @param other вектор, который должен быть вычтен из данного вектора; должен
         *              иметь ту же длину, что и данный вектор
         * @return новый вектор, представляющий разность данного и другого векторов
         */
        public Vector subtract(Vector other) {
            if(items.length != other.items.length) {
                throw new IllegalArgumentException("Для разности векторов они должны быть одной длины");
            }
            int [] vecDiff = new int[items.length];
            for(int i = 0; i < items.length; i++) {
                vecDiff[i] = items[i] - other.items[i];
            }
            return new Vector(vecDiff);
        }

        /**
         * Вычисляет скалярное произведение данного вектора с другим вектором.
         *
         * @param other вектор, с которым должно быть вычислено скалярное произведение;
         *              должен иметь ту же длину, что и данный вектор
         * @return скалярное произведение данного и другого векторов
         */
        public int dotProduct(Vector other) {
            if(items.length != other.items.length) {
                throw new IllegalArgumentException("Для вычисления скалярного произведения векторов должны быть одной длины");
            }
            int summ = 0;
            for(int i = 0; i < items.length; i++) {
                int itemsProd = items[i] * other.items[i];
                summ = summ + itemsProd;
            }
            return summ;
        }

        /**
         * Умножает данный вектор на скаляр.
         *
         * @param scalar скалярное значение, на которое должен быть умножен данный вектор
         * @return новый вектор, представляющий результат умножения данного вектора на скаляр
         */
        public Vector scalarMultiply(int scalar) {
            int [] vecMult = new int[items.length];
            for(int i = 0; i < items.length; i++) {
                vecMult[i] = scalar * items[i];
            }
            return new Vector(vecMult);
        }

        /**
         * Вычисляет длину (норму) данного вектора.
         */
        public double length() {
            int summ = 0;
            for(int i = 0; i < items.length; i++) {
                int itemsSqu = items[i] * items[i];
                summ = summ + itemsSqu;
            }
            return Math.sqrt(summ);
        }

        @Override
        public String toString() {
            return Arrays.toString(items);
        }
    }

    /**
     * Представляет матрицу (m x n)
     */
    public static class Matrix {
        private final int nRows;
        private final int nCols;
        private final int[][] rows;

        /**
         * Создает матрицу (nRows x nCols)
         */
        public Matrix(int nRows, int nCols) {
            this.nRows = nRows;
            this.nCols = nCols;
            this.rows = new int[nRows][nCols];
        }

        /**
         * Выводит матрицу в консоль построчно с правым выравниванием чисел по столбцам.
         */
        @Override
        public String toString() {
            int[] colWidths = new int[nCols];
            for (int col = 0; col < nCols; col++) {
                int maxWidth = 0;
                for (int row = 0; row < nRows; row++) {
                    int width = Integer.toString(rows[row][col]).length();
                    maxWidth = Math.max(maxWidth, width);
                }
                colWidths[col] = maxWidth;
            }

            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < nRows; row++) {
                for (int col = 0; col < nCols; col++) {
                    String cell = String.format("%" + colWidths[col] + "d", rows[row][col]);
                    sb.append(cell);
                    if (col < nCols - 1) {
                        sb.append(" ");
                    }
                }
                if (row < nRows - 1) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        }

        /**
         * Складывает текущую матрицу с другой матрицей.
         *
         * @param other другая матрица, должна иметь такую же размерность, как и
         *              текущая матрица
         * @return новая матрица, являющаяся результатом сложения
         */
        public Matrix add(Matrix other) {
            if (nCols != other.nCols || nRows != other.nRows) {
                throw new IllegalArgumentException("Для суммирования матриц они должны быть одной размерности");
            }
            Matrix summMatrix  = new Matrix(nRows, nCols);
            for (int iRow = 0; iRow < nRows; iRow++) {
                for (int jCol = 0; jCol < nCols; jCol++) {
                    summMatrix.rows[iRow][jCol] = this.rows[iRow][jCol] + other.rows[iRow][jCol];
                }

            }
            return summMatrix;
        }

        /**
         * Вычитает другую матрицу из текущей матрицы.
         *
         * @param other другая матрица, должна иметь такую же размерность, как и
         *              текущая матрица
         * @return новая матрица, являющаяся результатом вычитания
         */
        public Matrix subtract(Matrix other) {
            if (nCols != other.nCols || nRows != other.nRows) {
                throw new IllegalArgumentException("Для вычисления разности матриц они должны быть одной размерности");
            }
            Matrix summMatrix  = new Matrix(nRows, nCols);
            for (int iRow = 0; iRow < nRows; iRow++) {
                for (int jCol = 0; jCol < nCols; jCol++) {
                    summMatrix.rows[iRow][jCol] = this.rows[iRow][jCol] - other.rows[iRow][jCol];
                }
            }
            return summMatrix;
        }

        /**
         * Умножает текущую матрицу на другую матрицу.
         *
         * @param other другая матрица, количество строк которой должно быть равно
         *              количеству столбцов текущей матрицы
         * @return новая матрица, являющаяся результатом умножения
         */
        public Matrix multiply(Matrix other) {
            if (nCols != other.nRows) {
                throw new IllegalArgumentException("Для умножения двух матриц количество столбцов " +
                        "первой матрицы \nдолжно быть равно количеству строк второй матрицы");
            }
            Matrix multiMatrix = new Matrix(nRows, other.nCols);
            for( int iRow = 0; iRow < nRows; iRow++) {
                for (int jCol = 0; jCol < other.nCols; jCol++ ) {
                    multiMatrix.rows[iRow][jCol] = 0;
                    for (int k = 0; k < nCols; k++) {
                        multiMatrix.rows[iRow][jCol] = multiMatrix.rows[iRow][jCol] + this.rows[iRow][k]*other.rows[k][jCol];
                    }
                }
            }
            return multiMatrix;
        }

        /**
         * Умножает текущую матрицу на скаляр.
         *
         * @param scalar скалярное значение для умножения матрицы
         * @return новая матрица, являющаяся результатом умножения на скаляр
         */
        public Matrix scalarMultiply(int scalar) {
            Matrix scalMatrix = new Matrix(nRows, nCols);
            for(int iRow = 0; iRow < nRows; iRow++) {
                for (int jCol = 0; jCol < nCols; jCol++) {
                    scalMatrix.rows[iRow][jCol] = this.rows[iRow][jCol] * scalar;
                }
            }
            return scalMatrix;
        }

        /**
         * Транспонирует текущую матрицу.
         *
         * @return новая матрица, являющаяся транспонированной версией текущей матрицы
         */
        public Matrix transpose() {
            Matrix transMatrix = new Matrix(nRows, nCols);
            for(int iRow = 0; iRow < nRows; iRow++) {
                for (int jCol = 0; jCol < nCols; jCol++) {
                    transMatrix.rows[iRow][jCol] = this.rows[jCol][iRow];
                }
            }
            return transMatrix;
        }

        /**
         * Вычисляет определитель текущей матрицы.
         *
         * @return значение определителя матрицы
         */
        public int determinant() {
            return determinant(this.rows);
        }

        /**
         * Рекурсивно вычисляет определитель матрицы matrix.
         * Алгоритм использует разложение по первой строке матрицы.
         *
         * @param matrix квадратная матрица, для которой нужно вычислить определитель
         * @return определитель матрицы matrix
         */
        private static int determinant(int[][] matrix) {
            // Базовый случай для матрицы 1x1
            if (matrix.length == 1) {
                return matrix[0][0];
            }

            // Базовый случай для матрицы 2x2
            if (matrix.length == 2) {
                return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            }

            int result = 0;
            // Итерация по элементам первой строки матрицы
            for (int i = 0; i < matrix.length; i++) {
                // Создание матрицы для поддетерминанта
                int[][] smallerMatrix = new int[matrix.length - 1][matrix.length - 1];
                //todo(); // Заполнение smallerMatrix нужными значениями из matrix
                for (int row = 0; row < matrix.length - 1; row++) {
                    int colInd = 0;
                    for (int col = 0; col < matrix.length; col++) {
                        if (col != i) {
                            smallerMatrix[row][colInd] = matrix[row + 1][col];
                            colInd = colInd + 1;
                        }
                    }
                }
                // Вычисление поддетерминанта рекурсивным вызовом
                int subDeterminant = determinant(smallerMatrix);

                // Определение знака для элемента i разложения
                int sign = (i % 2 == 0) ? 1 : -1;

                // Обновление результата с учетом элемента i разложения, его знака и поддетерминанта
                result += sign * matrix[0][i] * subDeterminant;
            }
            return result;
        }

    }

    public static void main(String[] args) {
        // Пример создания нулевого вектора длины 3
        Vector zeroVector = new Vector(3);
        System.out.println("Нулевой вектор длины 3: " + zeroVector);

        // Пример создания вектора с заданными элементами
        int[] items = {1, 2, 3};
        Vector vectorA = new Vector(items);
        System.out.println("Вектор A: " + vectorA);

        // Пример сложения двух векторов
        int[] itemsB = {4, 5, 6};
        Vector vectorB = new Vector(itemsB);
        System.out.println("Вектор B: " + vectorB);
        Vector sumVector = vectorA.add(vectorB);
        System.out.println("Сумма векторов A и B: " + sumVector);

        // Пример вычитания двух векторов
        Vector diffVector = vectorA.subtract(vectorB);
        System.out.println("Разность векторов A и B: " + diffVector);

        // Пример скалярного произведения двух векторов
        int dotProduct = vectorA.dotProduct(vectorB);
        System.out.println("Скалярное произведение векторов A и B: " + dotProduct);

        // Пример умножения вектора на скаляр
        int scalar = 2;
        Vector scalarProduct = vectorA.scalarMultiply(scalar);
        System.out.println("Умножение вектора A на скаляр 2: " + scalarProduct);

        // Пример вычисления длины (нормы) вектора
        double length = vectorA.length();
        System.out.println("Длина (норма) вектора A: " + length);

        // Создание матрицы A 2x2
        Matrix A = new Matrix(2, 2);
        A.rows[0] = new int[]{1, 2};
        A.rows[1] = new int[]{3, 4};
        System.out.println("Matrix A:");
        System.out.println(A.toString());

        // Создание матрицы B 2x2
        Matrix B = new Matrix(2, 2);
        B.rows[0] = new int[]{2, 0};
        B.rows[1] = new int[]{1, 2};
        System.out.println("Matrix B:");
        System.out.println(B.toString());

        // Сложение матриц A и B
        Matrix C = A.add(B);
        System.out.println("Matrix A + B:");
        System.out.println(C.toString());

        // Вычитание матриц B из A
        Matrix D = A.subtract(B);
        System.out.println("Matrix A - B:");
        System.out.println(D.toString());

        // Умножение матриц A и B
        Matrix E = A.multiply(B);
        System.out.println("Matrix A * B:");
        System.out.println(E.toString());

        // Умножение матрицы A на скаляр
        int scalar3 = 3;
        Matrix F = A.scalarMultiply(scalar3);
        System.out.println("Matrix A * " + scalar3 + ":");
        System.out.println(F.toString());

        // Транспонирование матрицы A
        Matrix G = A.transpose();
        System.out.println("Matrix A Transpose:");
        System.out.println(G.toString());

        // Создание квадратной матрицы 3x3
        Matrix H = new Matrix(3, 3);
        H.rows[0] = new int[]{4, 3, 2};
        H.rows[1] = new int[]{1, 3, 1};
        H.rows[2] = new int[]{2, 1, 4};
        System.out.println("Matrix H:");
        System.out.println(H.toString());

        // Вычисление определителя матрицы H
        int det = H.determinant();
        System.out.println("Determinant of Matrix H:");
        System.out.println(det);

    }
}
