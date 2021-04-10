package org.mobile;

import io.dropwizard.Application;
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

    }

    @Override
    public void run(final DropwizardPlaygroundConfiguration configuration,
                    final Environment environment) {
        final HelloResource helloResource = new HelloResource();
        environment.jersey().register(helloResource);
    }

}
