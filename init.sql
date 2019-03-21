DROP SCHEMA if exists cam360_graphql_test cascade;
CREATE SCHEMA cam360_graphql_test;

SET search_path = cam360_graphql_test;
set schema 'cam360_graphql_test';

CREATE TABLE cam360_graphql_test.customer (
  id int  NOT NULL,
  name text,
  address text NOT NULL
);

insert into customer(ID,NAME,address) values(1, 'Akhil', 'STL');
insert into customer(ID,NAME,address) values(2, 'Jon', 'Wall');
insert into customer(ID,NAME,address) values(3, 'Rob', 'Brige');

