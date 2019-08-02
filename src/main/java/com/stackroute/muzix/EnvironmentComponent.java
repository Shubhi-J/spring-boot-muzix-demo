package com.stackroute.muzix;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentComponent  implements EnvironmentAware {
    private String message;

    public String getMessage() {
        return message;
    }

    public EnvironmentComponent setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.message = environment.getProperty("spring.datasource.url");

    }
}
