package com.first.demoMongo.businessControllers;

import com.first.demoMongo.repositories.MovieIT;
import com.first.demoMongo.repositories.UserIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MoviesControllerIT.class
})
public class AllbusinessControllesIntegrationTest {
}
