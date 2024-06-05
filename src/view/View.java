
package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import model.Game;

public class View extends JFrame {
	private Game game;
	private JButton[][] jb;
	private JPanel jp_menu;
	private JPanel jp_game;
	private JMenuItem jb_newgame;
	private JMenuItem jb_continue;
	private JMenuBar level;
	private JMenuItem jitem_level3;
	private JMenuItem jitem_level4;
	private JMenuItem jitem_level5;
	private ActionListener al;

	

	public View() {
        this.setTitle("Swap Puzzle Game");
        this.setSize(400, 400);
        this.setLocation(580, 120);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        init();
        
        this.setVisible(true);
			
	        
	    }
	 
	private void init() {
		JMenuBar menu = new JMenuBar();

		jb_newgame  = new JMenuItem("Chơi mới");
		jb_continue  = new JMenuItem("Chơi tiếp");
	
		level = new JMenuBar();
		JMenu jb_level = new JMenu("Level");
		jitem_level3 = new JMenuItem("Level 3");
		jitem_level4 = new JMenuItem("Level 4");
		jitem_level5 = new JMenuItem("Level 5");
		jb_level.add(jitem_level3);
		jb_level.add(jitem_level4);
		jb_level.add(jitem_level5);
		level.add(jb_level);
		menu.add(jb_newgame);
		menu.add(jb_continue);
		menu.add(level);
		
		jp_menu = new JPanel(new FlowLayout());
		jp_menu.add(jb_newgame);
		jp_menu.add(jb_continue);
		jp_menu.add(level);

		this.setLayout(new BorderLayout());
		this.add(jp_menu,BorderLayout.NORTH);

        al = new Controller(this);
        jb_newgame.addActionListener(al);
        jb_continue.addActionListener(al);
        jitem_level3.addActionListener(al);
        jitem_level4.addActionListener(al);
        jitem_level5.addActionListener(al);
        
	}
	
	public void paint(Game game,int size) {
		if(jp_game!=null) {
			this.clearView();
		}
		jp_game = new JPanel(new GridLayout(size, size));
        this.jb = new JButton[size][size];
       
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            		if(game.getGame()[i][j].getValue()!= 0) {
            			jb[i][j] = new JButton(""+ game.getGame()[i][j].getValue());
//	            			jb[i][j].setBackground(new Color(225, 75, 75));
            			jb[i][j].setFont(new Font("Arial", Font.BOLD, 30));
            			jb[i][j].setForeground(new Color(225, 75, 75));
            			jb[i][j].addActionListener(al);
                        jp_game.add(jb[i][j]);	
                        
            		}else {
            			jb[i][j] = new JButton("");
            			jb[i][j].setBackground(Color.WHITE);
            			jp_game.add(jb[i][j]);	
            		}   
            }
        }
      
        this.add(jp_game,BorderLayout.CENTER);
        this.setVisible(true);
    }
	
	 public JButton getJButton(int i, int j) {
	        return jb[i][j];
	    }
	 public void clearView() {
		    jp_game.removeAll();
		}
	 public void warning() {
		 JOptionPane.showMessageDialog(null, "Không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);

	 }
	 public void winGame() {
		 this.clearView();
		 JOptionPane.showMessageDialog(null, "Bạn đã chiến thắng", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
		
	 }

	public JMenuItem getJb_newgame() {
		return jb_newgame;
	}


	public void setJb_newgame(JMenuItem jb_newgame) {
		this.jb_newgame = jb_newgame;
	}


	public JMenuItem getJb_continue() {
		return jb_continue;
	}


	public void setJb_continue(JMenuItem jb_continue) {
		this.jb_continue = jb_continue;
	}
	
	public JMenuItem getJitem_level3() {
		return jitem_level3;
	}


	public void setJitem_level3(JMenuItem jitem_level3) {
		this.jitem_level3 = jitem_level3;
	}


	public JMenuItem getJitem_level4() {
		return jitem_level4;
	}


	public void setJitem_level4(JMenuItem jitem_level4) {
		this.jitem_level4 = jitem_level4;
	}


	public JMenuItem getJitem_level5() {
		return jitem_level5;
	}


	public void setJitem_level5(JMenuItem jitem_level5) {
		this.jitem_level5 = jitem_level5;
	}

	

	public JPanel getJp_game() {
		return jp_game;
	}


	public void setJp_game( JPanel jp_game) {
		this.jp_game = jp_game;
	}

	public static void main(String[] args) {
		
		View view = new View();
		
		
	}
	
	
}



