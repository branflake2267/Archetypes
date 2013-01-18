package org.gonevertical.project.server.domain;

public class SystemUser {

  private Long version;
  
  private Long id;

  private String googleUserId;

  private String googleEmail;

  private String googleNickname;

  private String loginUrl;

  private String logoutUrl;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGoogleUserId() {
    return googleUserId;
  }

  public void setGoogleUserId(String googleUserId) {
    this.googleUserId = googleUserId;
  }

  public String getGoogleEmail() {
    return googleEmail;
  }

  public void setGoogleEmail(String googleEmail) {
    this.googleEmail = googleEmail;
  }

  public String getGoogleNickname() {
    return googleNickname;
  }

  public void setGoogleNickname(String googleNickname) {
    this.googleNickname = googleNickname;
  }

  public String getLoginUrl() {
    return loginUrl;
  }

  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  public String getLogoutUrl() {
    return logoutUrl;
  }

  public void setLogoutUrl(String logoutUrl) {
    this.logoutUrl = logoutUrl;
  }
 
}
