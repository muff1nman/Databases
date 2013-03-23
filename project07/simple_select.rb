#!/usr/bin/env ruby

require 'mysql'

password = 'birdsdontfly'

db = Mysql::new(
  'csci403.c99q7trvwetr.us-west-2.rds.amazonaws.com',
  'ademaria',
  password,
  'snacks',
  3306)

