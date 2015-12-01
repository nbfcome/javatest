package com.sina.ea.modules.newsso;

public interface SsoSession {

    public Long getUid();

    public String getUser();

    public Long getBeginTime();

    public Long getExpiredTime();

}