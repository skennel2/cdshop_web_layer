package org.almansa.webapp.dto;

import java.util.Date;

public class LoginSessionModel {
    public String loginId;
    public Date loginDate;

    public LoginSessionModel(String loginId, Date loginDate) {
        super();
        this.loginId = loginId;
        this.loginDate = loginDate;
    }    
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
