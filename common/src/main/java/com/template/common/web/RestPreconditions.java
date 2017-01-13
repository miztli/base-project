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
package com.template.common.web;

import com.template.common.web.exception.BadRequestException;
import com.template.common.web.exception.ConflictException;
import com.template.common.web.exception.ResourceNotFoundException;

/**
 *
 * @author miztli
 */
public class RestPreconditions {

    /**
     *
     */
    public RestPreconditions() {
        throw new AssertionError();
    }
    
    /**
     * Verifies if the parameter passed to the method is not null
     * 
     * @param <T> Type of the object reference
     * @param reference An object reference
     * @return The reference <T> that was validated
     * 
     * @throws ResourceNotFoundException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference){
        return checkNotNull(reference, "Reference cannot be null");
    }
    
    /**
     *
     * @param <T> Type of the object reference
     * @param reference An object reference
     * @param message The message of the exception if the check fails
     * @return The reference <T> that was validated
     * 
     * @throws ResourceNotFoundException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, final String message) {
        if(reference == null){
            throw new ResourceNotFoundException(message);
        }
        return reference;
    }
    
    /**
     * Verifies if the parameter passed to the method is not null
     * 
     * @param <T> Type of the object reference
     * @param reference An object reference
     * @return The reference <T> that was validated
     * 
     * @throws BadRequestException if {@code reference} is null
     */
    public static <T> T checkRequestElementNotNull(final T reference){
        return checkRequestElementNotNull(reference, "");
    }
    
    /**
     *
     * @param <T> Type of the object reference
     * @param reference An object reference
     * @param message The message of the exception if the check fails
     * @return The reference <T> that was validated
     * 
     * @throws BadRequestException if {@code reference} is null
     */
    public static <T> T checkRequestElementNotNull(T reference, String message) {
        if(reference == null){
            throw new BadRequestException(message);
        }
        return reference;
    }
    
    /**
     * 
     * @param expression A boolean expression
     * 
     * @throws ConflictException if {@code expression} is false
     */
    public static void checkRequestState(final boolean expression){
        checkRequestState(expression, "Id must not be null");
    }
    
    /**
     *
     * @param expression A boolean expression
     * @param message The message of the exception if the check fails
     * 
     * @throws ConflictException if {@code expression} is false
     */
    public static void checkRequestState(final boolean expression, final String message) {
        if (!expression) {
            throw new ConflictException(message);
        }
    }
}
