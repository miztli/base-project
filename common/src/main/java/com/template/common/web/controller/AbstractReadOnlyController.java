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
package com.template.common.web.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.template.common.persistence.model.IEntity;
import com.template.common.persistence.service.IRawService;
import com.template.common.web.RestPreconditions;
import com.template.common.web.exception.ResourceNotFoundException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author miztli
 */
public abstract class AbstractReadOnlyController <T extends IEntity>{
    protected final Logger logger = Logger.getLogger(getClass());
    
    protected Class<T> clazz;

    public AbstractReadOnlyController(Class<T> clazz) {
        
        Preconditions.checkNotNull(clazz);
        this.clazz = clazz;
        
    }
    
    // find one
    
    protected final T findOneInternal(final long id){
        return RestPreconditions.checkNotNull(getService().findOne(id));
    }
    
    // find all
    
    protected final List<T> findAllInternal(final HttpServletRequest request){
        if(request.getParameterNames().hasMoreElements()){
            throw new ResourceNotFoundException();
        }
        
        return getService().findAll();
    }
    
    
    // find paginated and sorted
    
    protected final List<T> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder){
        final Page<T> resultPage = getService().findAllPaginatedAndSortedRaw(page, size, sortBy, sortOrder);
        
        if(page > resultPage.getTotalPages()){
            throw new ResourceNotFoundException();
        }
        
        return Lists.newArrayList(resultPage.getContent());
    }
    
    // find all paginated
    
    protected final List<T> findPaginatedInternal(final int page, final int size){
        final List<T> resultPage = getService().findAllPaginated(page, size);
                
        return resultPage;
    }
    
    // find all sorted
    
    protected final List<T> findAllSortedInternal(final String sortBy, final String sortOrder){
        final List<T> resultPage = getService().findAllSorted(sortBy, sortOrder);
        
        return resultPage;
    }    
    
    // count
    
    protected final long countInternal(){
        return getService().count();
    }
    
    /**
     * Counts all {@link Privilege} resources in the system
     * @return The total number of resouces in the system
     */
    @RequestMapping(method = RequestMethod.GET, value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return countInternal();
    }
    
    // template method

    protected abstract IRawService<T> getService();
}
