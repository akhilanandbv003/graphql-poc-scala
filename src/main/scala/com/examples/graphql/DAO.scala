package com.examples.graphql

import com.examples.graphql.config.BaseConfig
import scalikejdbc.{AutoSession, ConnectionPool, DB, DBSession, SQL}


class DAO() {
  //    def allCustomers = db.run(Customers.result)
  def allCustomerPostgres = CanonicalGenericDao.queryDB("select * customer")
}


class GenericDao {
  def updateDB(query: String) =
    DB localTx { implicit session => SQL(query).update.apply() }

  def queryDB(query: String): List[Map[String, Any]] =
    DB readOnly { implicit session =>
      SQL(query).map(_.toMap).list.apply()
    }

}

object CanonicalGenericDao extends GenericDao {

  def openTestConnection() = {
    implicit val session: DBSession = AutoSession
    Class.forName(BaseConfig.postgresCiDriver)
    ConnectionPool.singleton(BaseConfig.postgresCiConnectionString, BaseConfig.postgresCiUser, BaseConfig.postgresCiPassword)

    SQL(scala.io.Source.fromFile("init.sql").mkString).execute.apply()
  }

}


