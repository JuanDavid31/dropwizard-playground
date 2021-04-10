package org.mobile;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mobile.resources.HelloResource;

public class DropwizardPlayground extends Application<DropwizardPlaygroundConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardPlayground().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard Playground";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardPlaygroundConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<DropwizardPlaygroundConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(DropwizardPlaygroundConfiguration conf) {
                return conf.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final DropwizardPlaygroundConfiguration configuration,
                    final Environment environment) {
        final HelloResource helloResource = new HelloResource();
        environment.jersey().register(helloResource);
    }

}
