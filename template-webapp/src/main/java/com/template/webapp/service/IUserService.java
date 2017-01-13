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
package com.template.webapp.service;

import com.template.common.persistence.service.IService;
import com.template.webapp.persistence.model.User;
import java.util.List;

/**
 *
 * @author miztli
 */
public interface IUserService extends IService<User>{
    
    //Specific methods for User Entity
    
    public List<User> findByNameStartingWith(final String startsWith);    
    public List<User> findByLockedTrue();
    public User findByNameAndAge(final String name, final int age);    
    public List<User> findByEmailLike(String email);
    public User findbyEmailAndName(String email, String name);
    public User findByNameAndEmail(String email, String name);

}
