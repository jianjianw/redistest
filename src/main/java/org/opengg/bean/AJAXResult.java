package org.opengg.bean;

import java.io.Serializable;

public class AJAXResult implements Serializable{
    private static final long serialVersionUID = 6741980134374553513L;

    private boolean success;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
