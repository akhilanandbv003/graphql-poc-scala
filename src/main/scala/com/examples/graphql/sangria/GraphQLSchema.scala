package com.examples.graphql.sangria
import com.examples.graphql.models.Customer
import sangria.schema._
import sangria.macros.derive._
import sangria.schema.{Field, ListType, ObjectType}

object GraphQLSchema {

  val CustomerType = ObjectType[Unit,Customer](
    "Customer",
    fields[Unit, Customer](
      Field("id", IntType, resolve = _.value.id),
      Field("name", StringType, resolve = _.value.name),
      Field("address", StringType, resolve = _.value.address)

    )
  )

  val QueryType = ObjectType(
    "Query",
    fields[MyContext, Unit](
      Field("allCustomers", ListType(CustomerType), resolve = c => c.ctx.dao.allCustomerPostgres)

    )
  )
  val SchemaDefinition = Schema(QueryType)


}
