#! /usr/bin/env ruby
#
# Copyright © 2013 Andrew DeMaria (muff1nman) <ademaria@mines.edu>
#
# All Rights Reserved.

require 'mongo'
include Mongo

db = MongoClient.new("staff.mongohq.com", 10033).db("zoolicious")

db.authenticate( ENV['mongo_user'], ENV['mongo_pass'] )

puts db.collection_names
