import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class mineSweeper {
    Scanner input = new Scanner(System.in);
    Random rnd = new Random();
    int row;
    int column;
    String mineMap[][]; // Asıl haritayı tanımladık
    String frameMap[][];  // Kullanıcıya gorunecek olan harıtayı tanımladık
    int mineNumber;
    public void run(){
        System.out.println("Welcome to Game");
        System.out.println("Please enter number of row");
        row = input.nextInt();
        System.out.println("Please enter number of column");
        column = input.nextInt();
        mineNumber = ( row * column ) / 4 ;

        mineMap = new String[row][column];
        frameMap = new String[row][column];

        for (int i = 0 ; i < row ; i++){    // burada iki haritayi da boş gösterdik
            for (int j = 0 ; j < column ; j++){
                mineMap[i][j] = "-";
                frameMap[i][j] = "-";
            }
        }


        while(mineNumber > 0){  // burada mayın maritasına random mayınlari yerlestirdik.
            int rowMineIndex = rnd.nextInt(row);
            int columnMineIndex = rnd.nextInt(column);
            if (mineMap[rowMineIndex][columnMineIndex].equals("-")){
                mineMap[rowMineIndex][columnMineIndex] = "*";
                mineNumber--;
            }
        }


    }
}
