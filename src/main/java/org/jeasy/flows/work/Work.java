/*
 * The MIT License
 *
 *  Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.jeasy.flows.work;

import org.jeasy.flows.work.context.WorkContext;

import java.util.UUID;

/**
 * This interface represents a unit of work. Implementations of this interface must:
 * 
 * <ul>
 *     <li>catch any checked or unchecked exceptions and return a {@link WorkReport}
 *     instance with a status of {@link WorkStatus#FAILED} and a reference to the exception</li>
 *     <li>make sure the work is finished in a finite amount of time</li>
 * </ul>
 *
 * Work name must be unique within a workflow definition.
 * 
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public interface Work {

    /**
     * The name of the unit of work. The name must be unique within a workflow definition.
     * 
     * @return name of the unit of work.
     */
    default String getName() {
        return UUID.randomUUID().toString();
    }

    /**
     * Execute the unit of work and return its report. Implementations are required
     * to catch any checked or unchecked exceptions and return a {@link WorkReport} instance
     * with a status of {@link WorkStatus#FAILED} and a reference to the exception.
     * 
     * @param workContext context in which this unit of work is being executed
     * @return the execution report
     */
    WorkReport execute(WorkContext workContext);
}
