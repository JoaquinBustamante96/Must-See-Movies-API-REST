package com.first.demoMongo;

import com.first.demoMongo.businessControllers.AllbusinessControllesIntegrationTest;
import com.first.demoMongo.repositories.AllRepositoriesIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		AllRepositoriesIntegrationTest.class,
		AllbusinessControllesIntegrationTest.class,
})
public class AllTest {

	@Test
	public void contextLoads() {
	}

}
