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
import org.mobile.entity.Person;
import org.opentest4j.AssertionFailedError;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class DatabaseTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-config.yml");

    static final DropwizardAppExtension<DropwizardPlaygroundConfiguration> APP = new DropwizardAppExtension<>(
        DropwizardPlayground.class, CONFIG_PATH
    );

    private static Jdbi jdbi;
    private static PersonDao personDao;

    @BeforeAll
    static public void prepareDB() {
        try {
            dropDB();
            runMigrations();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JdbiFactory factory = new JdbiFactory();
        jdbi = factory.build(
            APP.getEnvironment(), APP.getConfiguration().getDataSourceFactory(), "postgres"
        );
        personDao = new PersonDao(jdbi);
    }

    private static void dropDB() throws Exception {
        APP.getApplication().run("db", "drop-all", "--confirm-delete-everything", CONFIG_PATH);
    }

    private static void runMigrations() throws Exception {
        APP.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    @Test
    public void daoIsNotNull() {
        Assertions.assertNotNull(jdbi);
        Assertions.assertNotNull(personDao);
    }

    @Test
    public void addValidPerson(){
        Optional<Person> personOpt = personDao.addPerson(new Person("Juan"));
        Assertions.assertTrue(personOpt.isPresent());
        Assertions.assertNotEquals(0, personOpt.get().getId());
    }

    @Test
    public void addInvalidNamePerson() {
        Assertions.assertThrows(Exception.class, () -> personDao.addPerson(new Person()));
    }

}
