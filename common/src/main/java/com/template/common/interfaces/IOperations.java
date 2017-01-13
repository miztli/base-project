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
package com.template.common.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author miztli
 * @param <T>
 */
public interface IOperations <T extends Serializable>{
 
    /**
     *
     * @param id Unique identifier for the resource
     * @return Resouce <T> if any is found
     */
    T findOne(final long id);
    
    /**
     *
     * @return
     */
    List<T> findAll();
    
    /**
     *
     * @param sortBy
     * @param sortOrder
     * @return Returns an ordered list with T elements
     */
    List<T> findAllSorted(final String sortBy, final String sortOrder);
    
    /**
     *
     * @param page Page number for pagination
     * @param size Size of the page
     * @return Returns a list with T elements
     */
    List<T> findAllPaginated(final int page, final int size);
    
    /**
     *
     * @param page Page number for pagination
     * @param size Size of the page
     * @param sortBy
     * @param sortOrder
     * @return Returns an ordered list with T elements
     */
    List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);

    /**
     *
     * @param resource Persists a resource 
     * @return Returns the persisted resource with a unique identifier
     */
    T create (final T resource);

    /**
     *
     * @param resource Updates the given resource
     */
    void update (final T resource);
    
    /**
     *
     * @param resouce Deletes the given resource
     */
    void delete (final long id);
    
    /**
     * Be careful when using this method, all persisted data may be lost
     */
    void deleteAll();
    
    /**
     *
     * @return The total count of persisted objects
     */
    long count();
}
