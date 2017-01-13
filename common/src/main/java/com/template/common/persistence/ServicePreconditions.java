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
package com.template.common.persistence.model;

import com.template.common.persistence.exception.EntityNotFoundException;
import com.template.common.web.exception.BadRequestException;

/**
 *
 * @author miztli
 */
public final class ServicePreconditions {

    public ServicePreconditions() {
        throw new AssertionError();
    }
    
    // API

    /**
     * Ensures that the entity reference passed as a parameter to the calling method is not null.
     * 
     * @param entity
     *            an object reference
     * @return the non-null reference that was validated
     * @throws MyEntityNotFoundException
     *             if {@code entity} is null
     */
    public static <T> T checkEntityExists(final T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    public static void checkEntityExists(final boolean entityExists) {
        if (!entityExists) {
            throw new EntityNotFoundException();
        }
    }

    public static void checkOKArgument(final boolean okArgument) {
        if (!okArgument) {
            throw new BadRequestException();
        }
    }
    
}
