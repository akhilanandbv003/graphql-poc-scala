package com.examples.graphql

import scala.language.postfixOps

object DBSchema {
  def startUpDb() = {
    CanonicalGenericDao.openTestConnection()
  }


}


