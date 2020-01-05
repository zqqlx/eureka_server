package net.xdclass.eureka_server.exception;

public enum BusinessExceptionConstnts implements CommonError{
    CODE_500("500","服务器内部异常!"),
    CODE_403("403","禁止访问!"),
    CODE_400("400","参数不合法");

    private String errCode;

    private String errMsg;

    BusinessExceptionConstnts(String code,String errorMessage) {
        this.errCode = code;
        this.errMsg = errorMessage;
    }

    @Override
    public String getErrCode() {
        // TODO Auto-generated method stub
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        // TODO Auto-generated method stub
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        // TODO Auto-generated method stub
        this.errMsg = errMsg;
        return this;
    }
}
