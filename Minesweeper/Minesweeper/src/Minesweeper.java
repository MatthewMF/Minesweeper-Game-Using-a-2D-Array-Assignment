public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over
    //private

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;

        this.initializeBoard();
        this.placeMines();
        this.calculateNumbers();
        

    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    private void initializeBoard() {
        //this.board = new char[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.cols; j++)
            {
                this.board[i][j] = '#';
                // this.revealed[i][j] = true;
            }
        }
        
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        int colsrange = this.cols;
        int rowsrange = this.rows;
        int count = this.numMines;

        while(count != 0 )
        {
            int randcol = (int)(Math.random() * colsrange);
            int randrow = (int)(Math.random() * rowsrange);

            if (!this.mines[randrow][randcol])
            {
                this.mines[randrow][randcol] = true; 
                count--;
            }
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private void calculateNumbers() {
        int count;
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.cols; j++)
            {
                if(this.board[i][j] != 'F' || !this.mines[i][j])
                {
                    count = 0;
                    try{
                        if(this.mines[i+1][j]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i+1][j+1]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i+1][j-1]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i][j+1]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i][j-1]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i-1][j]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i-1][j+1]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    try{
                        if(this.mines[i-1][j-1]){count++;}
                    }
                    catch(ArrayIndexOutOfBoundsException e){}

                    this.board[i][j] = (char)(count + 48);
                }
            }
        }
    }
    
    public boolean getReveal(){return this.revealed[0][0];}

    public void debugdisplayBoard() {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                //this.revealed[i][j] = true;
                if(this.revealed[i][j] == true)
                {
                    System.out.print("r ");
                }
                else{System.out.print("# ");}
            }

            System.out.println();
        }
    }
    public void minedisplayBoard() {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                //this.revealed[i][j] = true;
                if(this.mines[i][j] == true)
                {
                    System.out.print("m ");
                }
                else{System.out.print("# ");}
            }

            System.out.println();
        }
    }
    public void bdisplayBoard() {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                System.out.print(this.board[i][j] + " ");
            }

            System.out.println();
        }
    }

    

    // Method to display the current state of the board
    public void displayBoard() {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if(this.revealed[i][j] || this.board[i][j] == 'F') 
                    if(this.board[i][j] == '0') {System.out.print("- ");}
                    else{System.out.print(this.board[i][j] + " ");}
                else{System.out.print("# ");}
            }
            
            System.out.println();
        }
        //System.out.println(this.revealed[0][0]);
      /*  System.out.println();
        this.debugdisplayBoard();
        System.out.println();
        this.minedisplayBoard();
        System.out.println();
        this.bdisplayBoard();*/ 
    }


    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int inRow, int inCol, String inAction) {
        try
        {
            if (inAction.equals("reveal")){revealCell(inRow, inCol);}
            else if (inAction.equals("flag")){flagCell(inRow, inCol);}
            else if (inAction.equals("unflag")){unflagCell(inRow, inCol);}
            else{System.out.println("The player can either flag, unflag, and reveal! Please pick one!");}
        }
        catch(Exception e){System.out.println("Please choose a row and column that are within the bounds.");}
        finally{;}
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        int countFlag = 0; 
        int countRev = 0;
        int area = this.rows * this.cols;
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.cols; j++)
            {
                if(this.board[i][j] == 'F')
                    {
                        if(this.mines[i][j] == true){countFlag++;}
                    } 
                if(this.revealed[i][j] == true){countRev++;}
            }
        }
        if(area == countRev + countFlag){return true;}
        else{return false;}
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        if(this.revealed[row][col] && this.mines[row][col])
        {
            return true;
        }
        else{return false;}
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        if(this.board[row][col] != 'F')
        {
            if(this.mines[row][col])
            {
                this.revealed[row][col] = true;
            }
            this.cellFill(row, col);
            this.calculateNumbers();   
        }
    }

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        if(!this.revealed[row][col]) {this.board[row][col] = 'F';}
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        if(this.board[row][col] == 'F') {this.board[row][col] = '#';}
    }

    public void cellFill(int row, int col)
    {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
        {
            return;
        }
        if (this.mines[row][col] || this.revealed[row][col])
        {
            //System.out.println(this.board[row][col]);
            //System.out.print(this.revealed[row][col]);
            return;
        }
        
        this.revealed[row][col] = true; 
        if(this.board[row][col] == '0')
        {
            cellFill(row + 1, col);
            cellFill(row - 1, col);
            cellFill(row, col + 1);
            cellFill(row, col - 1);
            cellFill(row + 1, col + 1);
            cellFill(row - 1, col - 1);
            cellFill(row + 1, col - 1);
            cellFill(row - 1, col + 1);
        }
    } //not enough layers. Layer for bomb or not. Layer for revelaed or not. But where do I store infomration on whether a cell is flagged or not?
    //how to get broken prerimeter???
    //Show 2 many bombs 
}
