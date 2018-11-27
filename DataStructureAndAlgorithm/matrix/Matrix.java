package DataStructureAndAlgorithm.matrix;

import java.util.Scanner;

/**
 * 矩阵
 */
public class Matrix {
//    private T[][] matrix;//实数、虚数
//    暂时用int
    //actually一个建立一个父类/接口，分别继承
    private int [][] matrix;
    private Scanner scanner =null;
    private int row;
    private int column;

    //大小输入模式
    public Matrix() { //矩阵大小，维度
//        matrix =(T[][]) (new Object[row][column]);//有异常的
//        matrix = new int[row][column]; 如果仅在这里就会发生错误：下标越界：0； 原因：维数=0；
        scanner = new Scanner(System.in);
        System.out.println("Row:");
        this.row = scanner.nextInt();
        System.out.println("Column:");
        this.column = scanner.nextInt();
        matrix = new int[row][column];
        System.out.println("Please input matrix elements: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print("( " + i + " , " + j + " ) :");
                matrix[i][j] = scanner.nextInt();
            }
        }
        /**
        Scanner 如果关闭的话，会导致System.in关闭，结果无法再启用
         次  */
//        scanner.close();
    }

    //大小既定模式
    public Matrix(int row, int column){ //矩阵大小，维度
//        matrix =(T[][]) (new Object[row][column]);//有异常的
        matrix = new int[row][column];
        this.row= row;
        this.column =column;

    }
//加法
    public Matrix add(Matrix a,Matrix b){
        if (a.row == b.row &&a.column==b.column){
            Matrix c =new Matrix(a.row,a.column);
            for (int i=0;i<b.row;i++){
                for (int j = 0;j<b.column;j++) {
                    c.matrix[i][j] = a.matrix[i][j]+b.matrix[i][j];
                }
            }
            return c;
        }
        System.out.println("ERROR!");
        return null;
    }
    //减法
    public Matrix subtract(Matrix a,Matrix b){
        if (a.row == b.row &&a.column==b.column){
            Matrix c =new Matrix(a.row,a.column);
            for (int i=0;i<b.row;i++){
                for (int j = 0;j<b.column;j++) {
                    c.matrix[i][j] = a.matrix[i][j]-b.matrix[i][j];
                }
            }
            return c;
        }
        System.out.println("ERROR!");
        return null;
    }

    //转置
    public  Matrix transpose( ){
        for (int i=0;i< row-1;i++){
            for (int j = i+1;j< column;j++) {
                int temp =  matrix[i][j];
                 matrix[i][j] = matrix[j][i];
                 matrix[j][i]= temp;
            }
        }
        return this;
    }

    /**
     * 数乘
     * @param k
     * @return
     */
    public  Matrix multiply(int k){
        for (int i=0;i<row-1;i++){
            for (int j = i+1;j<column;j++) {
                matrix[i][j]= k*matrix[i][j];
            }
        }
        return this;
    }

    //矩阵乘法
    public  Matrix multiply(Matrix b){
        if (column==b.row) {
            Matrix c = new Matrix(row,b.column);
            for (int i = 0; i < row ; i++) {
                for (int j = 0; j < b.column; j++) {
                    for (int k =0; k<row;k++){
                        c.matrix[i][j] +=(matrix[i][k]*b.matrix[k][j]);
                    }
                }
            }
            return c;
        }
        System.out.println("ERROR!");
        return null;
    }


    public  void display(){
        System.out.println("Result : ");
        for (int i=0;i<row;i++){
            for (int j = 0;j<column;j++) {
                System.out.print(matrix[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
       matrix.transpose().display();
        Matrix matrix1 = new Matrix();
       matrix.multiply(matrix1).display();
    }

}
