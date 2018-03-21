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
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.sql.DataSource;

@Resource(name = "suite.r85.base.injection.resref.web.AdvResRefServletRequestListener/JNDI_Ann_DataSource", type = javax.sql.DataSource.class)
public class AdvResRefServletRequestListener implements ServletRequestListener {
    private static final String CLASS_NAME = AdvResRefServletRequestListener.class.getName();
    private final static Logger svLogger = Logger.getLogger(CLASS_NAME);

    // A map of the DataSources to be tested.
    HashMap<String, DataSource> map;

    // The JNDI Names that will need to be looked up
    String[] JNDI_NAMES = {
                            CLASS_NAME + "/dsFldXMLBnd",
                            CLASS_NAME + "/dsMthdXMLBnd",
                            CLASS_NAME + "/JNDI_Ann_DataSource",
    };

    /* Annotation targets */
    @Resource(name = "ResRef_DS")
    DataSource dsFldAnnBnd;
    DataSource dsMthdAnnBnd;

    /* XML targets */
    DataSource dsFldXMLBnd;
    DataSource dsMthdXMLBnd;

    public AdvResRefServletRequestListener() {
        map = new HashMap<String, DataSource>();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // Do Nothing
    }

    /**
     * This will populate the map of object to test. It
     * will then send that map off to be tested. Lastly, the list JNDI names are
     * handed of to the test framework to ensure they can be looked up and
     * tested.
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        svLogger.info("Request Initialized in the listener");
        String testName = sre.getServletRequest().getParameter("testMethod");
        if (testName != null && testName.equals("testResRefRequestListener")) {
            String key = WCEventTracker.KEY_LISTENER_INIT_AdvResRefServletRequestListener;
            populateMap();
            ResRefTestHelper.processRequest(key, map);
            ResRefTestHelper.testJNDILookupWrapper(key, JNDI_NAMES);
        }
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