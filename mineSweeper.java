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
    public void run() {
        System.out.println("Welcome to Game");     // Kullanıcıdan harıtanın kac elemanlı oldugunu gırmesı ıcın row ve column degerı aldık
        System.out.print("Please enter number of row :");
        row = input.nextInt();
        System.out.print("Please enter number of column :");
        column = input.nextInt();
        mineNumber = (row * column) / 4;


        mineMap = new String[row][column];
        frameMap = new String[row][column];

        for (int i = 0; i < row; i++) {    // burada iki haritayi da boş gösterdik
            for (int j = 0; j < column; j++) {
                mineMap[i][j] = "-";
                frameMap[i][j] = "-";
            }
        }


        while (mineNumber > 0) {  // burada mayın maritasına random mayınlari yerlestirdik.
            int rowMineIndex = rnd.nextInt(row);
            int columnMineIndex = rnd.nextInt(column);
            if (mineMap[rowMineIndex][columnMineIndex].equals("-")) {
                mineMap[rowMineIndex][columnMineIndex] = "*";
                mineNumber--;
            }
        }
        printFrameMap(); // kullanıcıya gorunecek olan haritayı yazdırdık
        gameCheck();  // oyunu baslattık
    }

        public void gameCheck(){
            boolean finish = false;
            while (!finish){
                System.out.print("Number of Row: "); // kullanicidab harita uzerınde degerler gırmesını ıstedık row ve column olacak sekılde
                int choosenRow = input.nextInt();
                System.out.print("Number of Column: ");
                int choosenColumn = input.nextInt();
                int mineNumber = 0;
                if (choosenRow < row && choosenColumn < column) {
                    if (mineMap[choosenRow][choosenColumn].equals("-") && frameMap[choosenRow][choosenColumn].equals("-")) {
                        for (int i = choosenRow - 1; i < choosenRow + 2; i++) {
                            for (int j = choosenColumn - 1; j < choosenColumn + 2; j++) {
                                if ((i >= 0) && (j >= 0) && (i < row) && (j < column) && (mineMap[i][j].equals("*"))) {
                                    mineNumber++;
                                }
                            } // Kullanıcıdan alınan hucre degerlerını kontrol eden algorıtma
                        }
                        frameMap[choosenRow][choosenColumn] = Integer.toString(mineNumber);
                        printFrameMap();
                        if (!checkWin()) {
                            System.out.println("You won the game!! Congratulations!!!");
                            printMineMap();
                            finish = true;
                        }
                    } else if (frameMap[choosenRow][choosenColumn].equals("*")) {
                        System.out.println("You found a mine. YOU LOST!!");
                        printMineMap();
                        finish = true;
                    } else if (!frameMap[choosenRow][choosenColumn].equals("-")) {
                        System.out.println("Please enter a different cell!!");
                    }
                } else {
                    System.out.println("Wrong cell.. Please enter a cell inside the map");
                }

            }
        }
        public void printFrameMap(){ // kullanıcıya gosterılecek olan ekranı yazdıran fonksıyon
            for (String[] row:frameMap){
                for (String column:row){
                    System.out.print(column + " ");
                }
                System.out.println();
            }
            System.out.println(" ========================== ");
        }

        public void printMineMap(){  // Mayın haritasını ekrana yazdıran fonksiyon. Oyun bitiminde ekrana yazdırılıyor
            for (String[] row:mineMap){
                for (String column : row){
                    System.out.print(column + " ");
                }
                System.out.println();
            }
            System.out.println(" ========================== ");
        }

        boolean checkWin(){ // oyunun kazanılıp kazanılmadıgını kontrol eden fonksıyon
            int emptyCell = 0;
            int minedCell = 0;
            for (int i = 0 ; i < frameMap.length ; i ++){
                for (int j = 0 ; j < frameMap[i].length; j++){
                    if (frameMap[i][j].equals("-")){
                        emptyCell++;
                    }
                    if (mineMap[i][j].equals("*")){
                        minedCell++;
                    }
                }
            }
            if (emptyCell == minedCell){
                return false;
            }
            return true;
        }
}

