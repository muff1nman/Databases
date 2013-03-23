#!/usr/bin/env ruby

require 'mysql'

password = 'XXXXX'

begin
  db = Mysql::new(
    'csci403.c99q7trvwetr.us-west-2.rds.amazonaws.com',
    'ademaria',
    password,
    'snacks',
    3306)

  results = db.query "SELECT name FROM project_test"
  results.each { |row|
    puts row
  }
  results.free
rescue Mysql::Error
  puts "Mysql threw an error"
ensure
  db.close unless db.nil?
end



