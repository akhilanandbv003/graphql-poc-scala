package com.examples.graphql

import com.examples.graphql.models.{Customer}
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

object DBSchema {

  def createDatabase: DAO = {
    val db = Database.forConfig("h2mem")
    Await.result(db.run(databaseSetup), 10 seconds)
    new DAO(db)
  }

  class CustomerTable(tag: Tag) extends Table[Customer](tag, "Customer") {

    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def name = column[String]("NAME")

    def address = column[String]("ADDRESS")

    def * = (id, name, address).mapTo[Customer]

  }

  val Customers = TableQuery[CustomerTable]

  val databaseSetup = DBIO.seq(
    Customers.schema.create,

    Customers forceInsertAll Seq(
      Customer(1, "Akhil", "STL"),
      Customer(2, "Jon", "Wall"),
      Customer(3, "Rob", "Brige")
    )
  )

}


