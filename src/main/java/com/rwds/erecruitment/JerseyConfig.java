package com.rwds.erecruitment;

import com.rwds.erecruitment.user.controllers.CORSResponseFilter;
import com.rwds.erecruitment.user.controllers.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CORSResponseFilter.class);
        register(UserController.class);

    }

}