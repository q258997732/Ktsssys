package com.bob.ktssts.entity;

public class ResponseBean {

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString(){
		return String.format("code:%d, msg:%s, data:%s", code, msg, data);
    }

	public enum ECode {
	  INFO(100),
	  SUCCESS(200),
	  REDIRECT(300),
	  CLIENT_ERROR(400),
	  SERVER_ERROR(500);

	  private final int code;

	  ECode(int code) {
		this.code = code;
	  }

	  public int getCode() {
		return code;
	  }

	}
}
