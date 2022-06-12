import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
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
				
				if (key == KeyEvent.VK_LEFT && snake.getSmer() != 'R'){
					System.out.println("LEVO");
					snake.setSmer('L');
					}
		
				if (key == KeyEvent.VK_RIGHT && snake.getSmer() != 'L'){
					snake.setSmer('R');
					}
				
				if (key == KeyEvent.VK_DOWN && snake.getSmer() != 'U'){
					snake.setSmer('D');
					}
				
				if (key == KeyEvent.VK_UP && snake.getSmer() != 'D'){
					snake.setSmer('U');
					}
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		
		for (int i = Panel.ROB; i <= snake.getPolja()[0].length * Panel.KVADRAT + Panel.ROB; i = i + Panel.KVADRAT) {
			g.drawLine(i, Panel.ROB, i, snake.getPolja().length * Panel.KVADRAT + Panel.ROB);
		}
		
		for (int i = Panel.ROB; i <= snake.getPolja().length * Panel.KVADRAT + Panel.ROB; i = i + Panel.KVADRAT) {
			g.drawLine(Panel.ROB, i, snake.getPolja()[0].length * Panel.KVADRAT + Panel.ROB, i);
		}
		
		g.setColor(Color.GREEN);
		for (int i = 0; i < snake.getPolja().length; i++) {
          for (int j = 0; j < snake.getPolja()[0].length; j++) {
          	Polja[][] polja = snake.getPolja();
          	if (snake.getKacaPozicija().contains(polja[i][j])) {	
          		g.fillRect(i * Panel.KVADRAT + Panel.ROB, j * Panel.KVADRAT + Panel.ROB, Panel.KVADRAT, Panel.KVADRAT);
          		}
          	}
          }
		
		if (snake.isKoncano()) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			drawCenteredString(g, "Game over", new Rectangle(getHeight(), getWidth()), getFont());
//			g.setFont(new Font("TimesRoman", Font.PLAIN, Panel.KVADRAT)); 
//			g.drawString("Game\n over", getWidth() / 2, getHeight() / 2);
		}
//		g.setColor(Color.YELLOW);
//		g.fillRect(snake.getKacaPozicija().get(0).getX() * Panel.KVADRAT + Panel.ROB, snake.getKacaPozicija().get(0).getY() * Panel.KVADRAT + Panel.ROB, Panel.KVADRAT, Panel.KVADRAT);
		
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}

	
	public Snake getSnake() {
		return snake;
	}

}