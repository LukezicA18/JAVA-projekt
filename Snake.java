import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
	private Okno okno;
	private Polja[][] polja;
	private List<Polja> kacaPozicija = new ArrayList<>();
	protected List<Polja> jabolka = new ArrayList<>();
	protected final int OKNO_SIRINA = 600;
	protected final int OKNO_VISINA = 600;
	protected final int VelikostPredmeta = 25;
	protected final int SteviloPolj = (OKNO_VISINA * OKNO_SIRINA) / (VelikostPredmeta * VelikostPredmeta);
	protected final int Nakljucje = 17;
	protected final int st_vrstic = OKNO_SIRINA / VelikostPredmeta;
	protected final int st_stolpcev = OKNO_VISINA / VelikostPredmeta;
	protected int Time = 100;
	protected int stPobranihJabolk = 0;
	protected int stKorakov = 0;
	protected boolean zacni = false;
	private boolean koncano = false;
	private char smer;
	
	//Ustvarimo podlago za igro
	public Snake() {
		okno = new Okno(this);
		okno.setVisible(true);
		polja = ustvariPolja(st_vrstic, st_stolpcev);
	}
	
	public void snake() {
		setSmer('R');
		List<Polja> kaca = new ArrayList<>();
		kaca.add(new Polja((int)(st_vrstic * 0.4), (int)(st_stolpcev * 0.4)));
		// Dolzina kače
		for (int i = 1; i < 6; i++) {
			kaca.add(new Polja((int)(st_vrstic * 0.4) - i, (int)(st_stolpcev * 0.4)));
		}
		setKacaPozicija(kaca);

		while (true) {
			if (zacni) {
				if (! isKoncano()) {
					stKorakov++;
					if (stKorakov > Nakljucje || jabolka.size() == 0) {
						jabolka.add(novoJabolko());
						stKorakov = 0;
					}
					for (Polja k : kacaPozicija) {
						if (jabolka.contains(k)) {
							jabolka.remove(k);
						}}
					setKacaPozicija(premakni());
				try {
					Thread.sleep((int)(Time - stPobranihJabolk * 0.5));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}}}
			risi();
		}}
	
	public Polja novoJabolko() {
		Random random = new Random();
		int x = random.nextInt(st_stolpcev);
		int y = random.nextInt(st_vrstic);
		Polja jabolko = new Polja(x, y);
		while (kacaPozicija.contains(jabolko)) {
			x = random.nextInt(st_stolpcev);
			y = random.nextInt(st_vrstic);
			jabolko = new Polja(x, y);
		}
		return jabolko; // Naključna pozicija jabolka
	}
	
	public void ponovno() {
		List<Polja> jabolka = new ArrayList<>();
		setJabolka(jabolka);
		stPobranihJabolk = 0;
		stKorakov = 0;
		setSmer('R');
		List<Polja> kaca = new ArrayList<>();
		kaca.add(new Polja((int)(st_vrstic * 0.4), (int)(st_stolpcev * 0.4)));
		for (int i = 1; i < 6; i++)
			kaca.add(new Polja((int)(st_vrstic * 0.4) - i, (int)(st_stolpcev * 0.4)));
		setKacaPozicija(kaca);
		setKoncano(false);
	}

	public List<Polja> premakni() {
		Polja naslednjaTocka = null;
		Polja glava = getKacaPozicija().get(0);
		List<Polja> novaKacaPozicija = new ArrayList<>();
		char smer = getSmer();
		switch(smer) {
		case 'L':
			naslednjaTocka = glava.premakni(-1, 0);
			// Kač se zaleti v steno
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			// Kača se zaleti sama vase
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				if (jabolka.contains(naslednjaTocka)) {
					stPobranihJabolk++;
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size()));
				}
				else
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
		
		case 'R':
			naslednjaTocka = glava.premakni(1, 0);
			// Kača se zaleti v steno
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			// Kaca se zaleti sama vase
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				if (jabolka.contains(naslednjaTocka)) {
					stPobranihJabolk++;
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size()));
				}
				else
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
			
		case 'D':
			naslednjaTocka = glava.premakni(0, 1);
			// Kača se zaleti v steno
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			// Kača se zaleti sama vase
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				if (jabolka.contains(naslednjaTocka)) {
					stPobranihJabolk++;
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size()));
				}
				else
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
			
		case 'U':
			naslednjaTocka = glava.premakni(0, -1);
			// Kača se zaleti v steno
			if (naslednjaTocka.getX() < 0 || naslednjaTocka.getX() > st_stolpcev - 1 ||
					naslednjaTocka.getY() < 0 || naslednjaTocka.getY() > st_vrstic - 1) {
				setKoncano(true);
			}
			// Kača se zaleti sama vase
			if (getKacaPozicija().contains(naslednjaTocka)) {
				setKoncano(true);
			}
			if (!isKoncano()) {
				novaKacaPozicija.add(naslednjaTocka);
				if (jabolka.contains(naslednjaTocka)) {
					stPobranihJabolk++;
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size()));
				}
				else
					novaKacaPozicija.addAll(getKacaPozicija().subList(0, getKacaPozicija().size() - 1));
			}
			break;
		}		
		return novaKacaPozicija;
	}
	
	public Polja[][] ustvariPolja(int st_vrstic, int st_stolpcev) {
		Polja[][] pozicija = new Polja[st_vrstic][st_stolpcev];
		for (int j = 0; j < st_stolpcev; j++) {
			for (int i = 0; i < st_vrstic; i++) {
				pozicija[i][j] = new Polja(i, j);
			}
		}
		return pozicija;
	}
	
	public void risi() {
		if(okno != null) {
			okno.repaint();
	}}
	
	public List<Polja> getKacaPozicija() {
		return this.kacaPozicija;
	}

	public void setKacaPozicija(List<Polja> kacaPozicija) {
		this.kacaPozicija = kacaPozicija;
	}

	public List<Polja> getJabolka() {
		return jabolka;
	}

	public void setJabolka(List<Polja> jabolka) {
		this.jabolka = jabolka;
	}

	public boolean isKoncano() {
		return koncano;
	}

	public void setKoncano(boolean koncano) {
		this.koncano = koncano;
	}
	
	public char getSmer() {
		return smer;
	}
	
	public void setSmer(char smer) {
		this.smer = smer;
	}
	
	public int getVelikostPredmeta() {
		return VelikostPredmeta;
	}

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
	
	// Glavna veja za izvajanje
	public static void main(String[] args) {
	      new Snake().snake();
	}
}