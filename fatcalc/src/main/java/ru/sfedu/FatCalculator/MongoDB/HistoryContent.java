package ru.sfedu.FatCalculator.MongoDB;

import java.text.SimpleDateFormat;
import java.util.*;

public class HistoryContent {

    private String className;
    private String createDate;
    private String actor;
    private String methodName;
    private Object object;
    private String oldBean;
    private String newBean;

    public HistoryContent() {
    }

    public HistoryContent(String className, String methodName, Object object, String oldBean, String newBean) {
        this.className = className;
        this.createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());;
        this.methodName = methodName;
        this.object = object;
        this.oldBean = oldBean;
        this.newBean = newBean;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate() {
        this.createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setOldBean(String oldBean) {
        this.oldBean = oldBean;
    }

    public void setNewBean(String newBean) {
        this.newBean = newBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryContent that = (HistoryContent) o;
        return Objects.equals(className, that.className) && Objects.equals(createDate, that.createDate) && Objects.equals(actor, that.actor) && Objects.equals(methodName, that.methodName) && Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, createDate, actor, methodName, object);
    }

    @Override
    public String toString() {
        return "HistoryContent{" +
                "className='" + className + '\'' +
                ", createDate=" + createDate + '\'' +
                ", actor='" + actor + '\'' +
                ", methodName='" + methodName + '\'' +
                ", object=" + object +
                '}';

    }

}
