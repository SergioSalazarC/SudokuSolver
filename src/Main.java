import java.util.Arrays;
import java.util.Scanner;

/*
00 10 20 30 ...
01 11 21 31 ...
02 12 22 32 ...
03 13 23 33 ...
... ... ... ...
*/


public class Main {

    public static void pintarSudoku(int[][] s){
        System.out.println("╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗");
        System.out.println("║ "+s[0][0]+" │ "+s[1][0]+" │ "+s[2][0]+" ║ "+s[3][0]+" │ "+s[4][0]+" │ "+s[5][0]+" ║ "+s[6][0]+" │ "+s[7][0]+" │ "+s[8][0]+" ║");
        System.out.println("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        System.out.println("║ "+s[0][1]+" │ "+s[1][1]+" │ "+s[2][1]+" ║ "+s[3][1]+" │ "+s[4][1]+" │ "+s[5][1]+" ║ "+s[6][1]+" │ "+s[7][1]+" │ "+s[8][1]+" ║");
        System.out.println("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        System.out.println("║ "+s[0][2]+" │ "+s[1][2]+" │ "+s[2][2]+" ║ "+s[3][2]+" │ "+s[4][2]+" │ "+s[5][2]+" ║ "+s[6][2]+" │ "+s[7][2]+" │ "+s[8][2]+" ║");
        System.out.println("╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
        System.out.println("║ "+s[0][3]+" │ "+s[1][3]+" │ "+s[2][3]+" ║ "+s[3][3]+" │ "+s[4][3]+" │ "+s[5][3]+" ║ "+s[6][3]+" │ "+s[7][3]+" │ "+s[8][3]+" ║");
        System.out.println("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        System.out.println("║ "+s[0][4]+" │ "+s[1][4]+" │ "+s[2][4]+" ║ "+s[3][4]+" │ "+s[4][4]+" │ "+s[5][4]+" ║ "+s[6][4]+" │ "+s[7][4]+" │ "+s[8][4]+" ║");
        System.out.println("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        System.out.println("║ "+s[0][5]+" │ "+s[1][5]+" │ "+s[2][5]+" ║ "+s[3][5]+" │ "+s[4][5]+" │ "+s[5][5]+" ║ "+s[6][5]+" │ "+s[7][5]+" │ "+s[8][5]+" ║");
        System.out.println("╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
        System.out.println("║ "+s[0][6]+" │ "+s[1][6]+" │ "+s[2][6]+" ║ "+s[3][6]+" │ "+s[4][6]+" │ "+s[5][6]+" ║ "+s[6][6]+" │ "+s[7][6]+" │ "+s[8][6]+" ║");
        System.out.println("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        System.out.println("║ "+s[0][7]+" │ "+s[1][7]+" │ "+s[2][7]+" ║ "+s[3][7]+" │ "+s[4][7]+" │ "+s[5][7]+" ║ "+s[6][7]+" │ "+s[7][7]+" │ "+s[8][7]+" ║");
        System.out.println("╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        System.out.println("║ "+s[0][8]+" │ "+s[1][8]+" │ "+s[2][8]+" ║ "+s[3][8]+" │ "+s[4][8]+" │ "+s[5][8]+" ║ "+s[6][8]+" │ "+s[7][8]+" │ "+s[8][8]+" ║");
        System.out.println("╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝");
    }


    public static int[][] escribirSudoku(String input){
        String[] separado = input.split("\n");
        int[][] sol = new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sol[j][i]=separado[i].charAt(j)-48;
            }
        }
        return sol;

    }

    public static int[][] resolver(int[][] sudoku,int casillaX, int casillaY){
        int[][] solucion  = Arrays.copyOf(sudoku,9);
        if (sudoku[casillaX][casillaY]!=0){
            if(casillaX>=8 && casillaY>=8){
                return solucion;
            }
            else if(casillaX>=8){
                solucion =resolver(sudoku,0,casillaY+1);
            }
            else{
                solucion = resolver(sudoku,casillaX+1,casillaY);
            }
        }else{
            for(int i=1; i<=9;i++){
                boolean valido=true;

                for (int a=0;a<9 && valido;a++){
                    valido= sudoku[casillaX][a]!=i && valido;
                }
                for (int a=0;a<9 && valido;a++){
                    valido= sudoku[a][casillaY]!=i && valido;
                }
                int cuadX=casillaX/3;
                int cuadY=casillaY/3;

                for(int a=cuadX*3; a<cuadX*3+3 && valido;a++){
                    for(int b=cuadY*3; b<cuadY*3+3;b++){
                        valido= sudoku[a][b]!=i  && valido;
                    }
                }

                if(valido){
                    solucion[casillaX][casillaY]=i;

                    if(casillaX>=8 && casillaY>=8){
                        continue;
                    }
                    else if(casillaX>=8){
                        solucion =resolver(solucion,0,casillaY+1);
                    }
                    else{
                        solucion = resolver(solucion,casillaX+1,casillaY);
                    }

                    boolean resuelto =true;
                    for(int a=0;a<9;a++){
                        for(int b=0;b<9;b++){
                            resuelto = resuelto && solucion[a][b]!=0;
                        }
                    }

                    if(resuelto){
                        break;
                    }
                    else{
                        solucion[casillaX][casillaY]=0;
                        continue;
                    }
                }
            }
        }




        return solucion;

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Escribir sudoku:");
            String input = "";
            for(int i=0;i<9;i++){
                input= input + in.next() +"\n";
            }
            System.out.println();

            System.out.println("El sudoku escrito es:");
            int[][] auc = escribirSudoku(input);
            pintarSudoku(auc);
            System.out.println();

            System.out.println("El sudoku resuelto es");
            int[][] sol= resolver(auc,0,0);
            pintarSudoku(sol);
            System.out.println();



        }
        /*
        int[][] sudoku = new int[9][9];


        sudoku[0][0]=8;
        sudoku[2][1]=3;
        sudoku[3][1]=6;
        sudoku[1][2]=7;
        sudoku[4][2]=9;
        sudoku[6][2]=2;
        sudoku[1][3]=5;
        sudoku[5][3]=7;
        sudoku[4][4]=4;
        sudoku[5][4]=5;
        sudoku[6][4]=7;
        sudoku[3][5]=1;
        sudoku[7][5]=3;
        sudoku[2][6]=1;
        sudoku[7][6]=6;
        sudoku[8][6]=8;
        sudoku[2][7]=8;
        sudoku[3][7]=5;
        sudoku[7][7]=1;
        sudoku[1][8]=9;
        sudoku[6][8]=4;



        sudoku[3][0]=5;
        sudoku[6][0]=4;
        sudoku[1][1]=1;
        sudoku[2][1]=5;
        sudoku[8][1]=3;
        sudoku[4][2]=7;
        sudoku[8][2]=9;
        sudoku[2][3]=4;
        sudoku[6][3]=8;
        sudoku[7][3]=2;
        sudoku[0][4]=2;
        sudoku[3][4]=9;
        sudoku[7][4]=7;
        sudoku[0][5]=8;
        sudoku[1][6]=6;
        sudoku[5][6]=4;
        sudoku[3][7]=7;
        sudoku[4][7]=8;
        sudoku[5][7]=2;
        sudoku[0][8]=3;
        sudoku[1][8]=4;
        sudoku[5][8]=9;



        int[][] sol= resolver(sudoku,0,0);


        for(int ii=0;ii<9;ii++){
            for(int jj=0;jj<9;jj++){
                System.out.print(sol[jj][ii]+" ");
            }
            System.out.println();
        }
        System.out.println();

        */


    }
}
