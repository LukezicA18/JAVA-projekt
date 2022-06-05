import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
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
		setMinimumSize(new Dimension((int)(snake.OknoSirina * 1.1), (int)(snake.OknoVisina * 1.17)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		add(new Panel(snake), BorderLayout.CENTER);
		
		// Meni 
		JMenuBar meniVrstica = new JMenuBar();
		setJMenuBar(meniVrstica);
		JMenu meni = new JMenu("Igra");
		JMenuItem novaIgra = new JMenuItem("Nova igra");
		JMenuItem moznosti = new JMenuItem("Mo�nosti");
//		JMenuItem novaIgra = new JMenuItem("Nova igra");
//		JMenuItem novaIgra = new JMenuItem("Nova igra");
//		JMenuItem novaIgra = new JMenuItem("Nova igra");
		novaIgra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				snake.ponovno();
				}});
		
		moznosti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				drugoOkno = new JFrame();
				
				}});

		meni.add(novaIgra);
		meni.add(moznosti);
		meniVrstica.add(meni);
		//
		
		pack();
	}

	public Snake getSnake() {
		return snake;
	}
}