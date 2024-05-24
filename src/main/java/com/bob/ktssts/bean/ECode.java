package com.bob.ktssts.bean;

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
