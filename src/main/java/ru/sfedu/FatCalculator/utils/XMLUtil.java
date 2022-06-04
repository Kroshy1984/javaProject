package ru.sfedu.FatCalculator.utils;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "main")
public class XMLUtil<T> {
    @ElementList(inline = true, required = false)
    public List<T> list;

    public XMLUtil() {
    }

    public XMLUtil(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

