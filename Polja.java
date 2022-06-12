public class Polja {
	private int x;
	private int y;
	
	public Polja(int x, int  y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object p) {
		if (this == p) {
			return true;
		}
		else if (p == null) {
			return false;
		}
		if (this.getX() != ((Polja)p).getX()) {
			return false;
		}
		if (this.getY() != ((Polja)p).getY()) {
			return false;
		}
		return true;
	}
	
	public Polja premakni(int x, int y) {
		return new Polja(getX() + x, getY() + y);
	}
}