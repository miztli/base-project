/*
 * The MIT License
 *
 * Copyright 2016 miztli.
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
package com.template.webapp.persistence.setup;

import com.template.webapp.persistence.model.Role;
import com.template.webapp.persistence.model.User;
import com.template.webapp.service.IRoleService;
import com.template.webapp.service.IUserService;
import com.template.webapp.util.Profiles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author miztli
 */
@Component
@Profile(Profiles.DEPLOYED) //Only registers the class when the profile is deployed
public class DatabaseSetup implements ApplicationListener<ContextRefreshedEvent>{
    private static final Logger logger = Logger.getLogger(DatabaseSetup.class);
    
    @Autowired
    private IUserService iUserService;
    
    @Autowired
    private IRoleService iRoleService;
    
    private boolean setupDone;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        
        if(!setupDone){
            logger.info("Executing setup: Saving test users into the DB");
                        
            createUsers(createRoles());
            
            setupDone = true;
            logger.info("Setup completed");
            
        }
        
       
    }

    private void createUsers(Map<String, Role> roles) {
        logger.info("Creating users");
        
            iUserService.create(new User(null, "Mizael Rodriguez", "dark_coneja@hotmail.com", 22, "xxxy", Boolean.TRUE, roles.get("admin")));
            iUserService.create(new User(null, "Mizael Jolines", "dark_abeja@hotmail.com", 23, "xxxyser", Boolean.TRUE, roles.get("developer")));
            iUserService.create(new User(null, "Patricia Ávila", "paty@hotmail.com", 25, "xxxyy", Boolean.TRUE, roles.get("reader")));
            iUserService.create(new User(null, "Jess Ramírez", "jessy@hotmail.com", 26, "xxxyyyy", Boolean.TRUE, roles.get("tester")));
            iUserService.create(new User(null, "Juan Lizárraga", "juanito@hotmail.com", 27, "xxy", Boolean.TRUE, roles.get("admin")));
            iUserService.create(new User(null, "Ricardo Arenas", "ricardito@hotmail.com", 28, "xxyy", Boolean.FALSE, roles.get("developer")));
        
    }
    private Map<String, Role> createRoles() {
        logger.info("Creating roles");
        
        Map<String, Role> roles = new HashMap<>();
        
            roles.put("admin", iRoleService.create(new Role(null, "Admin")));
            roles.put("developer", iRoleService.create(new Role(null, "Developer")));
            roles.put("reader", iRoleService.create(new Role(null, "Reader")));
            roles.put("tester", iRoleService.create(new Role(null, "Tester")));
            roles.put("architect", iRoleService.create(new Role(null, "Architect")));
        
        return roles;
    }   

    
    
}
