package com.cs.dal.minesweeper;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;

import static com.cs.dal.minesweeper.loadGridPage.millisec;
import static com.cs.dal.minesweeper.loadGridPage.handler;
import static com.cs.dal.minesweeper.loadGridPage.runnable;
import static com.cs.dal.minesweeper.loadGridPage.sec;
import static com.cs.dal.minesweeper.mainActivity.No_of_Bombs;
import static com.cs.dal.minesweeper.loadGridPage.TimeBuff;

/**
 * Created by rohitgs on 10/13/17.
 */

public  class gamePlay {

    public static final int row_number = 9;
    public static final int column_number = 9;
    private Context context;
    public gamePlay(){ }
    private drawImageCell[][] MinesweeperGrid = new drawImageCell[row_number][column_number];


    /**
     * Method to create the grid
     */

    public  void createNewGrid(Context context){
        this.context = context;
        // create the grid and store it
        int[][] GeneratedGrid = placeMines(No_of_Bombs,row_number, column_number);
        setValueGrid(context,GeneratedGrid);
    }


    /**
     * Method to set value to the grid
     */

    private void setValueGrid( final Context context, final int[][] grid ){
        for( int x = 0 ; x < row_number ; x++ ){
            for( int y = 0 ; y < column_number ; y++ ){
                if( MinesweeperGrid[x][y] == null ){
                    MinesweeperGrid[x][y] = new drawImageCell(context,x,y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }


    /**
     * Method which decides where the mines are placed
     */
    public  int[][] placeMines( int bombnumber , final int row_number , final int column_number){
        // Random for generating numbers

        int [][] grid = new int[row_number][column_number];
        int minesPlaced = 0;
        Random random = new Random();
        while (minesPlaced <= No_of_Bombs) {
            int x = random.nextInt(row_number - 1);
            int y = random.nextInt(column_number - 1);

            if (grid[y][x] != -1) {
                grid[y][x] = -1;
                minesPlaced++;
            }
        }

        grid = calculateNeigboursHints(grid,row_number,column_number);

        return grid;
    }


    /**
     * Method to find neighbours hints as to where the mines are placed
     */
    private  int[][] calculateNeigboursHints( int[][] grid , final int row_number , final int column_number){
        for( int x = 0 ; x < row_number ; x++){
            for( int y = 0 ; y < column_number ; y++){
                grid[x][y] = getNeighbourNumber(grid,x,y);
            }
        }

        return grid;
    }

    /**
     * Method to find neighbours hints as to where the mines are placed
     */
    private  int getNeighbourNumber( final int grid[][] , final int x , final int y){
        if( grid[x][y] == -1 ){
            return -1;
        }

        int mines = 0;

        if( mineAt(grid,x - 1 ,y + 1)) mines++; // top-left
        if( mineAt(grid,x     ,y + 1)) mines++; // top
        if( mineAt(grid,x + 1 ,y + 1)) mines++; // top-right
        if( mineAt(grid,x - 1 ,y )) mines++; // left
        if( mineAt(grid,x + 1 ,y)) mines++; // right
        if( mineAt(grid,x - 1 ,y - 1)) mines++; // bottom-left
        if( mineAt(grid,x     ,y - 1)) mines++; // bottom
        if( mineAt(grid,x + 1 ,y - 1)) mines++; // bottom-right

        return mines;
    }

    /**
     * Method to find if a mine is placed in a particular position
     */
    public  boolean mineAt(final int [][] grid,int y, int x) {
        if (y >= 0 && y < 9 && x >= 0 && x < 9 && grid[y][x] == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to find if a mine is placed in a particular position
     */
    public drawImageCell getCellAt(int position) {
        int i = position % row_number;
        int j = position / row_number;
        return MinesweeperGrid[i][j];
    }

    /**
     * Method to find if a mine is placed in a particular position
     */
    public  drawImageCell getCellAt(int x, int y){
        return MinesweeperGrid[x][y];
    }

    public  void revealCell(int i, int j) {
        if( i >= 0 && j >= 0 && i < row_number && j < column_number && !getCellAt(i,j).isCellClicked() ) {
            getCellAt(i, j).setClicked();
            if (getCellAt(i, j).getValue() == 0) {

                revealCell(i - 1, j);
                revealCell(i + 1, j);
                revealCell(i, j - 1);
                revealCell(i, j + 1);
                revealCell(i - 1, j - 1);
                revealCell(i - 1, j + 1);
                revealCell(i + 1, j - 1);
                revealCell(i + 1, j + 1);
        }


            if( getCellAt(i,j).isBombPresent() ){
                onLosing();
            }
        }
        checkEnd();
    }

    /**
     * Method to set the flag
     */
    public void flag(int i, int j){
        boolean isFlagged = getCellAt(i,j).isFlagged();
        getCellAt(i,j).setFlagged(!isFlagged);
        getCellAt(i,j).invalidate();
    }


    /**
     * Method to reveal all the tiles if the game has finished
     **/

    private  boolean checkEnd(){
        int bombNotFound = No_of_Bombs;
        int notRevealed = row_number * column_number;
        for( int i = 0 ; i < column_number ; i++ ){
        for ( int j = 0 ; j < row_number ; j++ ){
                if( getCellAt(i,j).isFlagged() && getCellAt(i,j).isBombPresent() ){
                    bombNotFound--;
                }
                if( getCellAt(i,j).isCellReveal() || getCellAt(i,j).isFlagged() ){
                    notRevealed--;
                }
            }
        }

        if( bombNotFound == 0 && notRevealed == 0 ){
            Toast.makeText(context,"Congrats!!!Game won", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    /**
     * Method for on losing the game
     */

    private  void onLosing(){

        //Pause Stopwatch
        Toast.makeText(context,"Sorry!!Game lost", Toast.LENGTH_SHORT).show();
        TimeBuff += sec;

        GetCellInfo();
    }
    /**
     * ethod Reveal the cells
     */
    private void GetCellInfo() {
        handler.removeCallbacks(runnable);
        for ( int i = 0 ; i< row_number ; i++ ) {
            for (int j = 0; j < column_number; j++) {
                getCellAt(i,j).setReveal();
            }
        }
    }

}
