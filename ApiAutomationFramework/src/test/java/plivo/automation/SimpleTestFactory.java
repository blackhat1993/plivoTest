package plivo.automation;

import org.testng.annotations.Factory;

import channel.tests.PlivoTest;

public class SimpleTestFactory 
{
    // Add channelName and channelNewNametoRename;
	@Factory
    public Object[] factoryMethod() 
    {
        return new Object[] { new PlivoTest("cha20", "cha21"), new PlivoTest("cha22", "cha23"), new PlivoTest("cha24", "cha25"), new PlivoTest("cha26", "cha27"), new PlivoTest("cha28", "cha29") };
    }
}