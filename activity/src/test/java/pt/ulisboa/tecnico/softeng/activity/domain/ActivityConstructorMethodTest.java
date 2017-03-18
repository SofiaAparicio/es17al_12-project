package pt.ulisboa.tecnico.softeng.activity.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;



public class ActivityConstructorMethodTest {
	private ActivityProvider provider;

	@Before
	public void setUp() {
		this.provider = new ActivityProvider("XtremX", "ExtremeAdventure");
	}

	@Test
	public void success() {
		Activity activity = new Activity(this.provider, "Bush Walking", 18, 80, 25);

		Assert.assertTrue(activity.getCode().startsWith(this.provider.getCode()));
		Assert.assertTrue(activity.getCode().length() > ActivityProvider.CODE_SIZE);
		Assert.assertEquals("Bush Walking", activity.getName());
		Assert.assertEquals(18, activity.getMinAge());
		Assert.assertEquals(80, activity.getMaxAge());
		Assert.assertEquals(25, activity.getCapacity());
		Assert.assertEquals(0, activity.getNumberOfOffers());
		Assert.assertEquals(1, this.provider.getNumberOfActivities());
	}
	
	@Test(expected=ActivityException.class)
	public void testProviderNull(){
		Activity activity = new Activity(null, "Bush Walking",20,80,25);
	}
	
	@Test(expected=ActivityException.class)
	public void testNameNull(){
		Activity activity = new Activity(this.provider, null,20,80,25);
	}
	
	@Test(expected=ActivityException.class)
	public void testNameEmpty(){
		Activity activity = new Activity(this.provider, "           ", 20,80,25);
	}
	
	
	@Test(expected=ActivityException.class)
	public void testMinAge(){
		Activity activity = new Activity(this.provider, "Bush Walking", 17,80,25);
	}
	
	@Test(expected=ActivityException.class)
	public void testMaxAge(){
		Activity activity = new Activity(this.provider, "Bush Walking", 20,100,25);
	}
	
	@Test
	public void successMaxAge(){
		Activity activity= new Activity(this.provider, "Bush Walking",20,99,25);
	}
	
	
	@Test(expected=ActivityException.class)
	public void testAgeInterval(){
		Activity activity = new Activity(this.provider, "Bush Walking", 40,39,25);
	}
	
	@Test
	public void successInterval(){
		Activity activity = new Activity(this.provider, "Bush Walking", 40,40,25);
	}
	@Test(expected=ActivityException.class)
	public void testCapacityPositive(){
		Activity activity = new Activity(this.provider, "Bush Walking", 20,80,0);
	}
	
	@Test
	public void successCapacity(){
		Activity activity= new Activity(this.provider, "Bush Walking",20,99,1);
	}
	

	@After
	public void tearDown() {
		ActivityProvider.providers.clear();
	}

}
