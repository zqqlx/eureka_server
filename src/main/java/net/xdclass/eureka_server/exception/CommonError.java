package net.xdclass.eureka_server.exception;

public interface CommonError {
    public String getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
