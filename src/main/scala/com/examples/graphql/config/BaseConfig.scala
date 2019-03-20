package com.examples.graphql.config

import com.typesafe.config.ConfigFactory

object BaseConfig {
  val config = ConfigFactory.load()
  val postgresCiDriver = config.getString("db.postgres.ci.driver")
  val postgresCiConnectionString = config.getString("db.postgres.ci.connectionstring")
  val postgresCiUser = config.getString("db.postgres.ci.username")
  val postgresCiPassword = config.getString("db.postgres.ci.password")
}
