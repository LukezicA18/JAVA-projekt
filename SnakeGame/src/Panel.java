import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Panel extends JPanel{
	private Snake snake;
	protected static final int ROB = 20;
	protected static final int KVADRAT = 25;
	
	// Ustvarimo potek igre
	public Panel(Snake snake) {
		super();
		this.snake = snake;
		setFocusable(true);
		setBackground(Color.BLACK);
		addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				if (key == KeyEvent.VK_LEFT && snake.getSmer() != 'R')
					snake.setSmer('L');
				
				if (key == KeyEvent.VK_RIGHT && snake.getSmer() != 'L')
					snake.setSmer('R');
				
				if (key == KeyEvent.VK_DOWN && snake.getSmer() != 'U')
					snake.setSmer('D');
				
				if (key == KeyEvent.VK_UP && snake.getSmer() != 'D')
					snake.setSmer('U');
					
				if (key == KeyEvent.VK_SPACE)
					snake.zacni = true;
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}});
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (!snake.zacni) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, Panel.KVADRAT * 2));
			String zacetek = "Press SPACE to start!";
			FontMetrics fm3 = g.getFontMetrics();
			g.drawString(zacetek, (int)((getWidth() - fm3.stringWidth(zacetek)) / 2), (int)((getHeight() - fm3.getDescent())* 0.55));
		}
		else {
		g.setColor(Color.WHITE);
			
		for (int i = Panel.ROB; i <= snake.getPolja()[0].length * Panel.KVADRAT + Panel.ROB; i = i + Panel.KVADRAT)
			g.drawLine(i, Panel.ROB, i, snake.getPolja().length * Panel.KVADRAT + Panel.ROB);
		
		for (int i = Panel.ROB; i <= snake.getPolja().length * Panel.KVADRAT + Panel.ROB; i = i + Panel.KVADRAT)
			g.drawLine(Panel.ROB, i, snake.getPolja()[0].length * Panel.KVADRAT + Panel.ROB, i);
		
		for (int i = 0; i < snake.getPolja().length; i++) {
	  		for (int j = 0; j < snake.getPolja()[0].length; j++) {
				Polja[][] polja = snake.getPolja();
				if (! snake.getKacaPozicija().contains(snake.getJabolka()) && snake.getJabolka().contains(polja[i][j])) {
					  g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		          		g.setColor(Color.RED);
		          		g.fillOval(i * Panel.KVADRAT + Panel.ROB, j * Panel.KVADRAT + Panel.ROB, Panel.KVADRAT, Panel.KVADRAT);
          	}
          	else if (snake.getKacaPozicija().contains(polja[i][j])) {
          		g.setColor(Color.GREEN);
          		g.fillRect(i * Panel.KVADRAT + Panel.ROB, j * Panel.KVADRAT + Panel.ROB, Panel.KVADRAT, Panel.KVADRAT);
	          	if (i == snake.getKacaPozicija().get(0).getX() && j == snake.getKacaPozicija().get(0).getY()) {
	          		g.setColor(Color.YELLOW);
	          		g.fillRect(i * Panel.KVADRAT + Panel.ROB, j * Panel.KVADRAT + Panel.ROB, Panel.KVADRAT, Panel.KVADRAT);
          		}}}}}
		
		if (snake.isKoncano()) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, Panel.KVADRAT * 3));
			String text = "Game\n over";
			FontMetrics fm1 = g.getFontMetrics();
			g.drawString(text, (int)(getWidth() - fm1.stringWidth(text)) / 2, (int)(getHeight() - fm1.getDescent()) / 2);
			g.setFont(new Font("TimesRoman", Font.PLAIN, Panel.KVADRAT * 2));
			String score = "Score: " + snake.stPobranihJabolk;
			FontMetrics fm2 = g.getFontMetrics();
			g.drawString(score, (int)((getWidth() - fm2.stringWidth(score)) / 2), (int)(getHeight() * 4 / 3 - fm2.getDescent()) / 2);
		}}

	public Snake getSnake() {
		return snake;
	}
}
