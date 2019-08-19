
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package uncc.nbad;

import java.io.Serializable;

/**
 *
 * @author teluk
 */
public class Offer implements Serializable {
    String itemcode;
    String itemName;
    String requestedItemcode;
    String requestedItemName;
    String requestedFrom;
    String requestedTo;

    public Offer() {}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(String requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public String getRequestedItemName() {
        return requestedItemName;
    }

    public void setRequestedItemName(String requestedItemName) {
        this.requestedItemName = requestedItemName;
    }

    public String getRequestedItemcode() {
        return requestedItemcode;
    }

    public void setRequestedItemcode(String requestedItemcode) {
        this.requestedItemcode = requestedItemcode;
    }

    public String getRequestedTo() {
        return requestedTo;
    }

    public void setRequestedTo(String requestedTo) {
        this.requestedTo = requestedTo;
    }
}

