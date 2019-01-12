package com.first.demoMongo.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MovieIT.class,
        UserIT.class
})
public class AllRepositoriesIntegrationTest { }
