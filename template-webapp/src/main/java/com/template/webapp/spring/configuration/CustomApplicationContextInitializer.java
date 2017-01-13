/*
 * The MIT License
 *
 * Copyright 2016 Pivotal Software, Inc..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.template.webapp.spring.configuration;

import com.google.common.base.Preconditions;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 *
 * @author miztli
 */
public class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

    private final Logger logger = Logger.getLogger(CustomApplicationContextInitializer.class);
    
    private static final String ENV_TARGET = "envTarget";

    public CustomApplicationContextInitializer() {
        super();
    }
    
    /**
     * Configures the environment profile
     */
    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        
        String envTarget = null;
        
        try {
            envTarget = getEnvTarget(environment);
            
            environment.getPropertySources().addFirst(new ResourcePropertySource("classpath:environment-" + envTarget + ".properties"));

            final String activeProfiles = environment.getProperty("spring.profiles.active");
            environment.setActiveProfiles(activeProfiles.split(","));
            
            //Despliega perfiles
            for(String profileName : environment.getActiveProfiles()){
                logger.info("Perfil activo: "+profileName);
            }
            
            
        } catch (final IOException ioEx) {
            if (envTarget != null) {
                logger.warn("Didn't find environment-" + envTarget + ".properties in classpath so not loading it in the AppContextInitialized", ioEx);
            }
        }
    }
    
    /**
     * @param environment
     * @return The environment target variable.
     */
    private String getEnvTarget(final ConfigurableEnvironment environment) {
        String target = environment.getProperty(ENV_TARGET);
        
        if (target == null) {
            logger.info("Didn't find a value for {} in the current Environment!, using the default 'dev'" + ENV_TARGET);
            target = "dev";
        }else{
            logger.info("Deploying application with envTarget="+target);
        }

        return Preconditions.checkNotNull(target);
    }
    
}
