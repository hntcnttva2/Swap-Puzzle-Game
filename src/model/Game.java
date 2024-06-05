package model;



public class Game {
	private int size;
    private Node[][] game;
    int rowZero,columnZero;
	

    public Game(int size,int[][] array) {
		this.size = size;
		this.game = new  Node[this.size][this.size];
		for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                game[i][j] = new Node(i, j,array[i][j]);
                game[i][j].setRow(i);
                game[i][j].setColumn(j);
                if(game[i][j].getValue()==0) {
                    rowZero = i;
                    columnZero = j;
                }
            }
        }
	}
    
   


	public void setGame(Game game) {
    	this.size = game.size;
        this.game = new Node[this.size][this.size];
    	for(int i = 0; i < game.size; i++) {
            for(int j = 0; j < game.size; j++) {
                this.game[i][j].setValue(game.game[i][j].getValue());
                }
            }
    }
    
    
	 
    public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}   
    public int getRowZero() {
        return rowZero;
    }
    public void setRowZero(int rowZero) {
        this.rowZero = rowZero;
    }
    public int getColumnZero() {
        return columnZero;
    }
    public void setColumnZero(int columnZero) {
        this.columnZero = columnZero;
    }
    
    public Node findNode(int number) {
    	
    	for(int i = 0 ; i< this.size;i++) {
			for(int  j = 0 ;j< this.size;j++) {
				if(number == game[i][j].getValue()) {
					
					return game[i][j];
				}
			}
		}
		return null;
    	
    }
    
    public void Move(Node node) {
        // xem co gan o so 0
        if (Math.abs(node.getRow() - rowZero) + Math.abs(node.getColumn() - columnZero) == 1) {
            //  swap
            game[rowZero][columnZero].setValue(game[node.getRow()][node.getColumn()].getValue());
            node.setValue(0);
            // update vi tri o so 0
            rowZero = node.getRow();
            columnZero = node.getColumn();
        }
    }
    

    public Node[][] getGame() {
        return game;
    }
    public void outGame() {
        for(int i = 0;i<size;i++) {
            for(int j = 0;j<size;j++) {
                System.out.print(game[i][j].getValue()+" ");
            }
            System.out.println();
        }
    }
    public boolean winGame() {
        if(rowZero!= size -1 || columnZero != size-1)
        	return false;
        int count=1;
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++ ) {
        		if(i != rowZero || j != columnZero) {
        			if (this.game[i][j].getValue() != count)return false;
        			count++;
        		}
        	}
        }
        return true;
    }
}
