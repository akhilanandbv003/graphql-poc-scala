package com.examples.graphql.sangria
import com.examples.graphql.models.Link
import sangria.schema._
import sangria.macros.derive._
import sangria.schema.{Field, ListType, ObjectType}

object GraphQLSchema {

  val LinkType = ObjectType[Unit,Link](
    "Link",
    fields[Unit, Link](
      Field("id", IntType, resolve = _.value.id),
      Field("url", StringType, resolve = _.value.url),
      Field("description", StringType, resolve = _.value.description)

    )
  )

  val QueryType = ObjectType(
    "Query",
    fields[MyContext, Unit](
      Field("allLinks", ListType(LinkType), resolve = c => c.ctx.dao.allLinks)
    )
  )
  val SchemaDefinition = Schema(QueryType)


}
