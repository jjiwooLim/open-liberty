/*******************************************************************************
 * Copyright (c) 1998, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ejs.container;

/**
 * This exception is thrown to indicate a container error has occurred
 * while trying to creat a new EJB instance. <p>
 */

public class CreateFailureException
                extends ContainerException
{
    private static final long serialVersionUID = 8258962263818787828L;

    /**
     * Create a new <code>CreateFailureException</code> instance. <p>
     */

    public CreateFailureException(Throwable ex) {
        super(ex);
    } // CreateFailureException

} // CreateFailureException
