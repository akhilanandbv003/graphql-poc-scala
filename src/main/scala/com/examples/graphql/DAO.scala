package com.examples.graphql

import DBSchema._
import slick.jdbc.H2Profile.api._


  class DAO(db: Database) {
    def allLinks = db.run(Links.result)
  }

