import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	@Test
	public void testConstructorOnDivideByZero() {
		try{
			Rational r = new Rational (3,0);
			assertTrue(false);
		}
		catch(ArithmeticException e){
			assertTrue(true);
		}
	}


	@Test
	public void testAllOpsWithDenomOfOne() {

	}	
	@Test
	public void testReciprocal() {
		Rational r1 = new Rational (7,11);
		Rational r2 = r1.reciprocal();
		assertTrue(r2.getNumer()==11);
		assertTrue(r2.getDenom()==7);
		Rational r3 = new Rational (0,10);
		try{
			Rational r4 = r3.reciprocal();
			fail();
		}
		catch(ArithmeticException e){
			assertTrue(true);
		}
	}


	@Test
	public void testMultiply() {

	}


	@Test
	public void testDivide() {

	}


	@Test
	public void testAdd() {

	}


	@Test
	public void testSubtract() {

	}


}