package org.gonevertical.project.server.domain;

public class SystemUser {

  public static SystemUser findSystemUser(Long id) {
    SystemUser systemUser = new SystemUser();
    systemUser.setVersion(1l); // simulate the version
    systemUser.setId(1l);
    systemUser.setGoogleNickname("branflake2267");

    return systemUser;
  }

  private Long version;
  private Long id;
  private String googleUserId;
  private String googleEmail;
  private String googleNickname;
  private String loginUrl;
  private String logoutUrl;

  public SystemUser() {
  }

  private void setVersion(long version) {
    this.version = version;
  }

  public Long getVersion() {
    return version;
  }

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
