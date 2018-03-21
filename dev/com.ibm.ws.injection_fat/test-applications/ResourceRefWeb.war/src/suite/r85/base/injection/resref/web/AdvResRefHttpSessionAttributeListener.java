/*******************************************************************************
 * Copyright (c) 2014, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package suite.r85.base.injection.resref.web;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.sql.DataSource;

@Resource(name = "suite.r85.base.injection.resref.web.AdvResRefHttpSessionAttributeListener/JNDI_Ann_DataSource", type = javax.sql.DataSource.class)
public class AdvResRefHttpSessionAttributeListener implements HttpSessionAttributeListener {
    private static final String CLASS_NAME = AdvResRefHttpSessionAttributeListener.class.getName();
    private final static Logger svLogger = Logger.getLogger(CLASS_NAME);

    // A map of the DataSources to be tested.
    HashMap<String, DataSource> map;

    // The JNDI Names that will need to be looked up
    String[] JNDI_NAMES = {
                            CLASS_NAME + "/dsFldXMLBnd",
                            CLASS_NAME + "/dsMthdXMLBnd",
                            CLASS_NAME + "/JNDI_Ann_DataSource"
    };

    /* Annotation targets */
    @Resource(name = "ResRef_DS")
    DataSource dsFldAnnBnd;
    DataSource dsMthdAnnBnd;

    /* XML targets */
    DataSource dsFldXMLBnd;
    DataSource dsMthdXMLBnd;

    public AdvResRefHttpSessionAttributeListener() {
        map = new HashMap<String, DataSource>();
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        svLogger.info("Attribute Added...");
        doWork(WCEventTracker.KEY_LISTENER_ADD_AdvResRefHttpSessionAttributeListener);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        svLogger.info("Attribute removed...");
        doWork(WCEventTracker.KEY_LISTENER_DEL_AdvResRefHttpSessionAttributeListener);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        svLogger.info("Attribute replaced...");
        doWork(WCEventTracker.KEY_LISTENER_REP_AdvResRefHttpSessionAttributeListener);
    }

    /**
     * This will populate the map of object to test. It
     * will then send that map off to be tested. Lastly, the list JNDI names are
     * handed of to the test framework to ensure they can be looked up and
     * tested.
     */
    public void doWork(String key) {
        populateMap();
        ResRefTestHelper.processRequest(key, map);
        ResRefTestHelper.testJNDILookupWrapper(key, JNDI_NAMES);
    }

    /**
     * Clear the map to avoid duplicates. Then add all the object that are to be
     * tested
     */
    public void populateMap() {
        map.clear();
        map.put("dsFldAnnBnd", dsFldAnnBnd);
        map.put("dsMthdAnnBnd", dsMthdAnnBnd);
        map.put("dsFldXMLBnd", dsFldXMLBnd);
        map.put("dsMthdXMLBnd", dsMthdXMLBnd);
    }

    // Annotation Method targets
    @Resource(name = "ResRef_DS")
    public void setDataSourceAnnBnd(DataSource ds) {
        dsMthdAnnBnd = ds;
    }

    // XML Method targets
    public void setDataSourceXMLBnd(DataSource ds) {
        dsMthdXMLBnd = ds;
    }
}