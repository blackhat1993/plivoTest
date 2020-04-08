package channel.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TestA {

	@Test(priority = 2)
	public void testB() {
		
		System.out.println("This is parent");
		assertTrue(false);
	}
	
	
	@Test(priority = 1, dependsOnMethods = {"testB"})
	public void testC() {
		
		System.out.println("This is parent");
	}
	
	@Test(dependsOnMethods = {"testC"})
	public void testD() {
		
		System.out.println("This is parent");
	}
}
