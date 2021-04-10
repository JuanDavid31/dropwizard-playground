package org.mobile.db;

import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mobile.DropwizardPlayground;
import org.mobile.DropwizardPlaygroundConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class DatabaseTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-config.yml");

    static final DropwizardAppExtension<DropwizardPlaygroundConfiguration> APP = new DropwizardAppExtension<>(
        DropwizardPlayground.class, CONFIG_PATH
    );

    private static Jdbi jdbi;
    private static DaoPersona daoPersona;

    @BeforeAll
    static public void prepareDB() {
        JdbiFactory factory = new JdbiFactory();
        jdbi = factory.build(
            APP.getEnvironment(), APP.getConfiguration().getDataSourceFactory(), "postgres"
        );
        daoPersona = new DaoPersona(jdbi);
    }

    @Test
    public void daoIsNotNull() {
        Assertions.assertNotNull(jdbi);
        Assertions.assertNotNull(daoPersona);
    }
}