package com.moviesmustsee.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        MovieIT.class,
        UserIT.class,
        PasswordResetTokenIT.class,
})
public class AllRepositoriesIntegrationTest { }
