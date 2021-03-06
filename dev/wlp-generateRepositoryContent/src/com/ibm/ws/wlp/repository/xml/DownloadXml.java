/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * WLP Copyright IBM Corp. 2013
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.ibm.ws.wlp.repository.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "downloads")
public class DownloadXml {

    private List<DownloadItem> downloadItems;

    /**
     * @return the downloadItems
     */
    @XmlElement(name = "download")
    public List<DownloadItem> getDownloadItems() {
        if (this.downloadItems == null) {
            this.downloadItems = new ArrayList<DownloadItem>();
        }
        return this.downloadItems;
    }

}
