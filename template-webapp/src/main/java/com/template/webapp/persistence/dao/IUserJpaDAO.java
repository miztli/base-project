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
package com.template.webapp.persistence.dao;

import com.template.common.interfaces.IByNameApi;
import com.template.webapp.persistence.model.User;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author miztli
 */
public interface IUserJpaDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, IByNameApi<User>{
    
    //Example of a method with query creation from method name
    public List<User> findByNameStartingWith(final String startsWith);    
    public List<User> findByLockedTrue();
    
    //Example of a method with @Query and @Parameter    
    @Query("from User u where u.name = ?1 and u.age > ?2")
    public User findByNameAndAge(final String name, final int age);
    
    @Query("from User u where u.email like ?1%")
    public List<User> findByEmailLike(String email);
    
    // Example of a method using named parameters
    @Query("from User u where u.email = :email and u.name = :name")
    public User findbyEmailAndName(@Param("email") String email,@Param("name") String name);
    
    //Using Specification with Criteria API
    @Override
    public User findOne(Specification<User> spec);
    
    // NOTE: Native queries or Named queries can be used too, I prefer not to use them XD

    
}
