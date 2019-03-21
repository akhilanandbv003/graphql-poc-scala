package com.examples.graphql

import scalikejdbc.DBSession

import scala.language.postfixOps

object DBSchema {
  def startUpDb(): DAO = {
    val session: DBSession = CanonicalGenericDao.openTestConnection()
    new DAO(session)
  }


}


