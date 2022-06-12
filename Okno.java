import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Okno extends JFrame {	
	private JFrame drugoOkno;
	private Snake snake;
	public Okno(Snake snake) {
		super();
		this.snake = snake;
		setTitle("Snake");
		setMinimumSize(new Dimension((int)(snake.OKNO_SIRINA * 1.1), (int)(snake.OKNO_VISINA * 1.17)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Panel(snake), BorderLayout.CENTER);
		
		// Meni 
		JMenuBar meniVrstica = new JMenuBar();
		setJMenuBar(meniVrstica);
		JMenu meni = new JMenu("Igra");
		JMenuItem novaIgra = new JMenuItem("Nova igra");
		novaIgra.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				if (snake.isKoncano())
					snake.ponovno();
				}});
		
		meni.add(novaIgra);
		meniVrstica.add(meni);
		pack();
	}

	public Snake getSnake() {
		return snake;
	}
}