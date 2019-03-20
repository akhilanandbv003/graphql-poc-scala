package com.examples.graphql

import com.examples.graphql.models.Link
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

  class LinksTable(tag: Tag) extends Table[Link](tag, "LINKS") {

    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def url = column[String]("URL")

    def description = column[String]("DESCRIPTION")

    def * = (id, url, description).mapTo[Link]

  }

  val Links = TableQuery[LinksTable]

  val databaseSetup = DBIO.seq(
    Links.schema.create,

    Links forceInsertAll Seq(
      Link(1, "http://howtographql.com", "Awesome community driven GraphQL tutorial"),
      Link(2, "http://graphql.org", "Official GraphQL web page"),
      Link(3, "https://facebook.github.io/graphql/", "GraphQL specification")
    )
  )

}

