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
package com.template.webapp.service.impl;

import com.template.common.persistence.service.AbstractService;
import com.template.webapp.persistence.dao.IUserJpaDAO;
import com.template.webapp.persistence.dao.specifications.UserSpecifications;
import com.template.webapp.persistence.model.User;
import com.template.webapp.service.IUserService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author miztli
 */

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements IUserService{

    @Autowired
    private IUserJpaDAO dao;
   
    @Override
    public IUserJpaDAO getDao() {
        return dao;
    }   

    @Override
    protected JpaSpecificationExecutor<User> getSpecificationExecutor() {
        return dao;
    }    
        
    @Override
    public User findByName(String name) {        
        return getDao().findByName(name);
    } 

    @Override
    public List<User> findByNameStartingWith(String startsWith) {
        return getDao().findByNameStartingWith(startsWith);
    }

    @Override
    public List<User> findByLockedTrue() {
        return getDao().findByLockedTrue();
    }

    @Override
    public User findByNameAndAge(final String name, final int age) {
        return getDao().findByNameAndAge(name, age);
    }

    @Override
    public List<User> findByEmailLike(String email) {
        return getDao().findByEmailLike(email);
    }

    @Override
    public User findbyEmailAndName(String email, String name) {
        return getDao().findbyEmailAndName(email, name);
    }

    @Override
    public User findByNameAndEmail(String email, String name) {
        return getDao().findOne(UserSpecifications.getUserByNameAndEmail(email, name));
    }

    

}
