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
import com.template.webapp.persistence.dao.IRoleJpaDAO;
import com.template.webapp.persistence.model.Role;
import com.template.webapp.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miztli
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService{
    
    @Autowired
    private IRoleJpaDAO dao;

    public RoleServiceImpl() {
        super();
    }
    
    // API
    // get/find
    
    @Override
    public Role findByName(String name) {
        return dao.findByName(name);
    }
   
    // create

    @Override
    public Role create(Role entity) {
        return super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //Spring
    @Override
    protected PagingAndSortingRepository<Role, Long> getDao() {
        return dao;
    }

    
    @Override
    protected JpaSpecificationExecutor<Role> getSpecificationExecutor() {
        return dao;
    }

    
    
}
