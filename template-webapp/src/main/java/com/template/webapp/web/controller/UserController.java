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
package com.template.webapp.web.controller;

import com.template.common.persistence.service.IRawService;
import com.template.common.util.QueryConstants;
import com.template.common.web.controller.AbstractController;
import com.template.common.web.controller.ISortingController;
import com.template.webapp.persistence.model.Role;
import com.template.webapp.persistence.model.User;
import com.template.webapp.service.IUserService;
import com.template.webapp.util.Privileges;
import com.template.webapp.util.WebMappings;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author miztli
 */
@Controller
@RequestMapping(value = WebMappings.USERS)
public class UserController extends AbstractController<User> implements ISortingController<User>{

    @Autowired
    private IUserService service;
    
    public UserController(){
        super(User.class);
    } 
    

    @Override    
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_READ)
    public List<User> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) int page, 
                                                @RequestParam(value = QueryConstants.SIZE) int size, 
                                                @RequestParam(value = QueryConstants.SORT_BY) String sortBy, 
                                                @RequestParam(value = QueryConstants.SORT_ORDER) String sortOrder) {
        
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_READ)    
    public List<User> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) int page, 
                                       @RequestParam(value = QueryConstants.SIZE) int size) {
        return findPaginatedAndSortedInternal(page, size, null, null);
    }

    @Override
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_READ)    
    public List<User> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) String sortBy, 
                                    @RequestParam(value = QueryConstants.SORT_ORDER) String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_READ)    
    public List<User> findAll(HttpServletRequest request) {
        return findAllInternal(request);
    }
    
    // find - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_READ)
    public User findOne(@PathVariable("id") final Long id) {
        return findOneInternal(id);
    }

    // create

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final User resource) {
        createInternal(resource);
    }

    // update

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
//    @Secured(Privileges.CAN_USER_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final User resource) {
        updateInternal(id, resource);
    }

    // delete

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @Secured(Privileges.CAN_USER_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }
    
    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public User test(@PathVariable("id") final Long id) {
        return new User(id, "Mizael", "dark_coneja@hotmail.com", 22, "xxx", Boolean.TRUE, new Role(null, "test"));
        
    }
    @RequestMapping(value = "/findByNameStartingWith", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public List<User> findByNameStartingWith(@RequestParam("name") final String name) {
        logger.info("Finding user starting with: "+name);
       
        return getService().findByNameStartingWith(name);
        
    }
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public List<User> getActiveUsers() {
        logger.info("Finding active users");
       
        return getService().findByLockedTrue();
    }
    
    @RequestMapping(value = "/findByNameAndAge", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public User getUserByNameAndAge(@RequestParam("name") final String name, @RequestParam("age") final int age) {
        logger.info("Finding user: "+name+" with age: "+age);
       
        return getService().findByNameAndAge(name, age);
    }
    @RequestMapping(value = "/findByEmailLike", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public List<User> getUserByEmailLike(@RequestParam("email") final String email) {
        logger.info("Finding user with email like: "+email);
       
        return getService().findByEmailLike(email);
    }
    @RequestMapping(value = "/findbyEmailAndName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public User findbyEmailAndName(@RequestParam("email") final String email, @RequestParam("name") final String name) {
        logger.info("Finding user with email: "+email+" and name: "+name);
       
        return getService().findbyEmailAndName(email, name);
    }
    @RequestMapping(value = "/findByNameAndEmail", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @Secured(Privileges.CAN_USER_WRITE)
    public User findByNameAndEmail(@RequestParam("email") final String email, @RequestParam("name") final String name) {
        logger.info("Finding user with email: "+email+" and name: "+name);
       
        return getService().findByNameAndEmail(email, name);
    }
    
    
    // Spring
    
    @Override
    protected IUserService getService() {
        return service;
    }
    
}
