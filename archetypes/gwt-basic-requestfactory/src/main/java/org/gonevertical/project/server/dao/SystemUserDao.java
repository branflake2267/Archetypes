package org.gonevertical.project.server.dao;

import org.apache.log4j.Logger;
import org.gonevertical.project.server.domain.SystemUser;

public class SystemUserDao {
  private static Logger logger = Logger.getLogger(SystemUserDao.class.getName());

  public SystemUser findSystemUser(String id) {
    logger.info("findSystemUser(): id=" + id);
    return new SystemUser();
  }
  
  public SystemUser persist(SystemUser systemUser) {
    logger.info("persist(): systemUser=" + systemUser);
    return systemUser;
  }
  
  void remove(SystemUser systemUser) {
    logger.info("remove(): systemUser=" + systemUser);
  }
  
}
