package channel.tests;

import org.testng.annotations.Test;

public class TestB extends TestA {

	@Test(dependsOnMethods = {"testB"}, priority = 2)
	public void testA() {
		
		System.out.println("This is child");
	}
}
