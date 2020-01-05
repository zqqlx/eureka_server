package net.xdclass.eureka_server.exception;

public class BusinessException extends RuntimeException implements CommonError {
    private CommonError commonError;
    //直接接收EmBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;

    }
    //接收自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }
    @Override
    public String getErrCode() {
        // TODO Auto-generated method stub
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        // TODO Auto-generated method stub
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
