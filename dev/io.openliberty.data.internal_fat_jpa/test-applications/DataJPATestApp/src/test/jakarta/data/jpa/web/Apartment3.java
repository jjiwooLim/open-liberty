/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
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
package test.jakarta.data.jpa.web;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Entity with embeddable and mapped superclass
 * Entity has a field with a colliding delimited attribute name with mapped superclass.
 */
@Entity
public class Apartment3 extends Residence {

    @Id
    public long aptId;

    public boolean occupant_firstName;

    @Embedded
    public Bedroom quarters;

}
