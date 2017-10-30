public class Rational{

	//DATA GOES UP HERE
	private int numer, denom;
	public Rational(int numerIn, int denomIn){
		numer = numerIn;
		denom = denomIn;		
	}
	
	public int getNumer(){return numer;}
	
	public int getDenom(){return denom;}
	
	public String toString(){return numer + "/" + denom;}	

	public Rational reciprocal(){return new Rational(getDenom(), getNumer());}
	
	public static Rational multiply(Rational a, Rational b){
		return new Rational(a.getNumer()*b.getNumer(), a.getDenom()*b.getDenom());
	}
	
	public Rational divide(Rational a){return multiply(this, a.reciprocal());}
	
	public Rational add(Rational a){
		int addC = (getNumer()*a.getDenom()) + (getDenom()*a.getNumer());
		int addD = (getDenom()*a.getDenom());
		return new Rational (addC, addD);
	}
}
