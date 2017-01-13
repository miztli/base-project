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
package com.template.common.persistence.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.template.common.persistence.model.IEntity;
import com.template.common.persistence.model.ServicePreconditions;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author miztli
 */
@Transactional
public abstract class AbstractRawService<T extends IEntity> implements IRawService<T> {
    protected final Logger logger = Logger.getLogger(getClass());

    
//    @Autowired
//    protected ApplicationEvent eventPublisher;
    
    public AbstractRawService() {
        super();
    }

    // find - one
    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id) {
        return getDao().findOne(id);
    }

    //find - all
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }
    
    // find - all paginated and sorted and raw
    @Override
    @Transactional(readOnly = true)
    public Page<T> findAllPaginatedAndSortedRaw(int page, int size, String sortBy, String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        
        return getDao().findAll(new PageRequest(page, size, sortInfo));
    }
    
    // find - all paginated and sorted
    @Override
    @Transactional(readOnly = true)
    public List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        final List<T> content = getDao().findAll(new PageRequest(page, size, sortInfo)).getContent();
        if (content == null) {
            return Lists.newArrayList();
        }
        return content;
    }

    // find - all paginated
    @Override
    @Transactional(readOnly = true)
    public List<T> findAllPaginated(final int page, final int size) {
        final List<T> content = getDao().findAll(new PageRequest(page, size, null)).getContent();
        if (content == null) {
            return Lists.newArrayList();
        }
        return content;
    }

    // find - all sorted
    @Override
    @Transactional(readOnly = true)
    public List<T> findAllSorted(final String sortBy, final String sortOrder) {
        final Sort sortInfo = constructSort(sortBy, sortOrder);
        return Lists.newArrayList(getDao().findAll(sortInfo));
    }
    
    // persist

    @Override
    public T create(final T entity) {
        Preconditions.checkNotNull(entity);

        final T persistedEntity = getDao().save(entity);

        return persistedEntity;
    }

    // update

    @Override
    public void update(final T entity) {
        Preconditions.checkNotNull(entity);

        getDao().save(entity);
    }

    // delete

    @Override
    public void deleteAll() {
        getDao().deleteAll();
    }

    @Override
    public void delete(final long id) {
        final T entity = getDao().findOne(id);
        ServicePreconditions.checkEntityExists(entity);

        getDao().delete(entity);
    }

    // count

    @Override
    public long count() {
        return getDao().count();
    }

    
    
    // template method

    protected abstract PagingAndSortingRepository<T, Long> getDao();

    protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

    // template

    protected final Sort constructSort(final String sortBy, final String sortOrder) {
        Sort sortInfo = null;
        if (sortBy != null) {
            sortInfo = new Sort(Direction.fromString(sortOrder), sortBy);
        }
        return sortInfo;
    }
    
}
