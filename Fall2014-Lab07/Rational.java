public class Rational{
	private final int numer, denom;
	
	public Rational(int numerIn, int denomIn){
		numer = numerIn;
		denom = denomIn;
		if(denom==0) throw new ArithmeticException("Divide by Zero");
	}
	
	//The new copy constructor
	public Rational(Rational other){
		numer = other.getNumer();
		denom = other.getDenom();
	}
	
	public int getNumer(){return numer;}
	
	public int getDenom(){return denom;}
	
	public String toString(){return numer + "/" + denom;}
	
	public Rational reciprocal(){return new Rational(getDenom(), getNumer());}

	public static Rational multiply(Rational first, Rational second) {
		int multipliedNumer = first.getNumer() * second.getNumer();
		int multipliedDenom = first.getDenom() * second.getDenom();
		Rational r1 = new Rational(multipliedNumer, multipliedDenom);
		return r1;
	}
	
	public Rational divide(Rational otherRational){return multiply(this,otherRational.reciprocal());}
	
	public Rational add(Rational otherRational){
		int addC = (this.getNumer()*otherRational.getDenom()) + (this.getDenom()*otherRational.getNumer());
		int addD = (this.getDenom()*otherRational.getDenom());
		return new Rational (addC, addD);
	}
	
	//The new subtract
	public Rational subtract(Rational otherRational){
		int subtractC = (this.getNumer()*otherRational.getDenom()) - (this.getDenom()*otherRational.getNumer());
		int subtractD = (this.getDenom()*otherRational.getDenom());
		return new Rational (subtractC, subtractD);
	}
}
