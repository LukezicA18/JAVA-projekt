import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Snake {
	private Okno okno;
	private Polja[][] polja;
	private List<Polja> kacaPozicija = new ArrayList<>();
	protected final int OknoSirina = 600;
	protected final int OknoVisina = 600;
	protected final int VelikostPredmeta = 25;
	protected final int SteviloPolj = (OknoVisina * OknoSirina) / (VelikostPredmeta * VelikostPredmeta);
	protected final int Nakljucje = 29;
	protected final int Time = 50;
	protected final int st_vrstic = OknoSirina / VelikostPredmeta;
	protected final int st_stolpcev = OknoVisina / VelikostPredmeta;
	private char smer;
	private boolean koncano = false;
	
	
	//Ustvarimo podlago za igro
	public Snake() {
		okno = new Okno(this);
		okno.setVisible(true);
		polja = UstvariPolja(st_vrstic, st_stolpcev);	
	}

	public List<Polja> getKacaPozicija() {
		return kacaPozicija;
	}

	public void setKacaPozicija(List<Polja> kacaPozicija) {
		this.kacaPozicija = kacaPozicija;
	}

	public void dodajKacaPozicija() {
		getKacaPozicija();
	}
	
	public void snake() {	
		setSmer('R');
		List<Polja> kaca = new ArrayList<>();
		kaca.add(new Polja((int)(st_vrstic * 0.4), (int)(st_stolpcev * 0.4)));
		// Dol�ina ka�e
		for (int i = 1; i < 10; i++) {
			kaca.add(new Polja((int)(st_vrstic * 0.4) - i, (int)(st_stolpcev * 0.4)));
		}
		setKacaPozicija(kaca);
		while (true) {
			if (isKoncano()) {
				System.out.println("Konec");
			}
			else if (!isKoncano()) {
				setKacaPozicija(premakni());
				try {
					Thread.sleep(Time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			risi();
			
		}
	}
	
	public boolean isKoncano() {
		return koncano;
	}

	public void setKoncano(boolean koncano) {
		this.koncano = koncano;
	}
	
	
	//
	public void ponovno() {
		setKoncano(false);
		setSmer('R');
		List<Polja> kaca = new ArrayList<>();
		kaca.add(new Polja((int)(st_vrstic * 0.4), (int)(st_stolpcev * 0.4)));
		// Dol�ina ka�e
		for (int i = 1; i < 10; i++) {
			kaca.add(new Polja((int)(st_vrstic * 0.4) - i, (int)(st_stolpcev * 0.4)));
		}
		setKacaPozicija(kaca);
		
	}
	
	public List<Polja> premakni() {
		Polja naslednjaTocka = null;
		Polja glava = getKacaPozicija().get(0);
		List<Polja> novaKacaPozicija = new ArrayList<>();
		char smer = getSmer();
		switch(smer) {
		case 'L':
			naslednjaTocka = glava.premakni(-1, 0);
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
		
		case 'R':
			naslednjaTocka = glava.premakni(1, 0);
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
			
		case 'D':
			naslednjaTocka = glava.premakni(0, 1);
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
			
		case 'U':
			naslednjaTocka = glava.premakni(0, -1);
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
		}
		
		
		return novaKacaPozicija;
	}
	
	public char getSmer() {
		return smer;
	}

	public void setSmer(char smer) {
		this.smer = smer;
	}

	public void risi() {
		if(okno != null) {
			okno.repaint();
		}
	}
	
	public int getVelikostPredmeta() {
		return VelikostPredmeta;
	}
	
//	public int getSteviloPolj() {
//		return SteviloPolj;
//	}

	public int getNakljucje() {
		return Nakljucje;
	}

	public int getTime() {
		return Time;
	}

	public Polja[][] getPolja(){
		return polja;
	}
	
	public void setPolja(Polja[][] polja) {
		this.polja = polja;
	}
	
	public Okno getOkno() {
		return okno;
	}

	public void setOkno(Okno okno) {
		this.okno = okno;
	}
	
	public Polja[][] UstvariPolja(int st_vrstic, int st_stolpcev) {
		Polja[][] pozicija = new Polja[st_vrstic][st_stolpcev];
		for (int j = 0; j < st_stolpcev; j++) {
			for (int i = 0; i < st_vrstic; i++) {
				pozicija[i][j] = new Polja(i, j);
			}
		}
		return pozicija;
	}
		
	
	// Glavna veja za izvajanje
	public static void main(String[] args) {
	      new Snake().snake();
	    }
}
