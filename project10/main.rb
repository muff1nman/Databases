#! /usr/bin/env ruby
#
# Copyright © 2013 Andrew DeMaria (muff1nman) <ademaria@mines.edu>
#
# All Rights Reserved.

require 'mongo'
include Mongo
DB_URL = "mongodb://<user>:<password>@staff.mongohq.com:10033/zoolicious"

db = MongoClient.new("staff.mongohq.com", 10033).db("zoolicious")

db.authenticate("csci403", "wuwnosql403")

puts db.collection_names

habitats = db["habitats"]

habitats.find.each do |habitat|
  puts habitat
end

puts "==================="

habitats.find("zoo_id" => { "$exists" => true}).each do |h|
  puts h
end

puts "==================="

habitats.find("zoo_id" => { "$exists" => true}).each do |h|
  puts h
  zoo = db["zoos"].find_one("_id" => h["zoo_id"])
  puts zoo
end


