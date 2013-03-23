#!/usr/bin/env ruby

require 'mysql'

password = 'birdsdontfly'

begin
  db = Mysql::new(
    'csci403.c99q7trvwetr.us-west-2.rds.amazonaws.com',
    'ademaria',
    password,
    'snacks',
    3306)

rescue Mysql::Error
  puts "Mysql threw an error"
ensure
  db.close
end



