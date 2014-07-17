package com.cdol.busTest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class XmlData {
 
    @XmlElement(name = "items")
    private List<XmlBusData> items;
 
    public List<XmlBusData> getItems() {
        return items;
    }
 
    public void setItems(List<XmlBusData> items) {
        this.items = items;
    }
}
