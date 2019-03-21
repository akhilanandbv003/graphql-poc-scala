package com.examples.graphql

import com.examples.graphql.config.BaseConfig
import com.examples.graphql.models.Customer
import org.json4s._
import org.json4s.native.JsonMethods.parse
import scalikejdbc.{AutoSession, ConnectionPool, DB, DBSession, SQL}

import scala.collection.immutable

class DAO(session: DBSession) {
  //    def allCustomers = db.run(Customers.result)
  def allCustomerPostgres = {
    val data: immutable.Seq[Map[String, Any]] = CanonicalGenericDao.queryDB("select * from customer")
    //    implicit val formats = DefaultFormats
    //    parse(data).extract[Customer]
    import org.json4s.jackson.Serialization

    implicit val formats = org.json4s.DefaultFormats

    val jsonData = Serialization.write(data)
    parse(jsonData).extract[Seq[Customer]]




  }
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
    session
  }

}


