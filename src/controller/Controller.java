package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import model.Game;
import model.Node;
import view.View;

public class Controller implements ActionListener {
    private View view;
    private Game game;
    private int size = 3;
    public Controller(View view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
    	
    	String src = e.getActionCommand();
    	if(src == view.getJb_newgame().getText()) {
    		try {
				this.readFile(size);
				this.writeFileSaveGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
    	if(src == view.getJb_continue().getText()) {
			this.read_continue();
		}
//    	if(src== view.getJb_save().getText()) {
//    		this.writeFileSaveGame();
//    	}
    	if(src == view.getJitem_level3().getText()) {	
			size = 3;
		}
    	if(src == view.getJitem_level4().getText()) {
			size = 4;
		}
    	if(src == view.getJitem_level5().getText()) {
    		size = 5;
		}
    	try {
			int number = Integer.parseInt(src);	
			game.Move(game.findNode(number));
			this.writeFileSaveGame();
			view.paint(game, game.getSize());
			if(game.winGame()==true){
				view.winGame();
				game = null;
				this.clearFile();
			 }
			
			
			
			
		} catch (Exception e2) {
			// TODO: handle exception
		}

    }
    
    public void readFile(int size) throws IOException {
    	FileReader fr = null;
		BufferedReader br = null; 
		  try {
			  if(size == 3) {
				  fr = new FileReader("D:\\Java\\BTL_Final\\src\\source\\game_size_3.txt");
			  }
			  if(size == 4) {
				  fr = new FileReader("D:\\Java\\BTL_Final\\src\\source\\game_size_4.txt");
			  }
			  if(size == 5) {
				  fr = new FileReader("D:\\Java\\BTL_Final\\src\\source\\game_size_5.txt");
			  } 
			  
			  br = new BufferedReader(fr);  
	            String line;
	            ArrayList<int[][]> listOfArrays = new ArrayList<>();
	            int[][] array = new int[size][size];
	            int rowCount = 0;
	            
	            while ((line = br.readLine()) != null) {
	                if (!line.trim().isEmpty()) {
	                    String[] numbers = line.split("\\s+");
	                    for (int j = 0; j < numbers.length; j++) {
	                        array[rowCount][j] = Integer.parseInt(numbers[j]);
	                    }
	                    rowCount++;
	                    
	                    if (rowCount == size) {
	                        listOfArrays.add(array);
	                        array = new int[size][size];
	                        rowCount = 0;
	                    }
	                }
	            }	            
	            Random rd = new Random();
	            int a = rd.nextInt(listOfArrays.size());
	            array = listOfArrays.get(a);
	            game = new Game(size, array);
	            view.paint(game, size);
	        } finally {
	            if (br != null) {
	                br.close();
	            }
	            if (fr != null) {
	                fr.close();
	            }
	        }

    }
   public void read_continue() {
	   FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("D:\\Java\\BTL_Final\\src\\source\\game_save.txt");
			br = new BufferedReader(fr);
			String line;
			int[][] array = new int[100][100];
			int row = 0;
			int size = 0;
			while((line = br.readLine()) != null) {
				String number[]= line.split(" ");
				size = number.length;
				for (int column = 0; column < size; column++) {
					int value = Integer.parseInt(number[column]);
					array[row][column] = value;
				}
					row++;
				}
				if(row > 0) {
					 game = new Game(size, array);
			         view.paint(game, size);
				} else {
				view.warning();
				}
			
		}catch (Exception e2) {
			// TODO: handle exception
		}
   }
    public void writeFileSaveGame() {
    	FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("D:\\Java\\BTL_Final\\src\\source\\game_save.txt");
			bw = new BufferedWriter(fw);
			if(game!=null) {
				for (Node[] row1 : game.getGame()) {
					for (Node value : row1) {
						bw.write(Integer.toString(value.getValue()));
						bw.write(" ");
					}
					bw.newLine();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
    }
    public void clearFile() {
    	FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("D:\\Java\\BTL_Final\\src\\source\\game_save.txt");
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
    }
    
}