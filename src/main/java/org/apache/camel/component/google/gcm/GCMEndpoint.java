package org.apache.camel.component.google.gcm;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.component.google.gcm.configuration.GCMConfiguration;
import org.apache.camel.component.google.gcm.producer.http.SimpleSender;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

/**
 * Represents a GoogleCloudMessaging endpoint.
 */
@UriEndpoint(scheme = "google-cloud-messaging", title = "GoogleCloudMessaging", syntax = "google-cloud-messaging:name", consumerClass = GCMConsumer.class, label = "GoogleCloudMessaging")
public class GCMEndpoint extends DefaultEndpoint {

    @UriPath
    @Metadata(required = "true")
    private String name;

    @UriParam
    private GCMConfiguration configuration;

    public GCMEndpoint(String uri, GCMComponent component, GCMConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    public Producer createProducer() throws Exception {
        return new SimpleSender(this, this.configuration.getApiKey());
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        throw new RuntimeCamelException("Invalid. There is nothing to consume from GCM");
    }

    public boolean isSingleton() {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public GCMConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GCMConfiguration configuration) {
        this.configuration = configuration;
    }

}
