#! /usr/bin/env ruby
#
# Copyright © 2013 Andrew DeMaria (muff1nman) <ademaria@mines.edu>
#
# All Rights Reserved.

require 'mongo'
include Mongo

def display_divider
  divider = ""
  20.times { divider << "=" }
  divider
end

def display_section( string )
  puts string
  puts display_divider
  yield
  puts display_divider
  puts "\n\n"
end

db = MongoClient.new("staff.mongohq.com", 10033).db("zoolicious")

db.authenticate( ENV['mongo_user'], ENV['mongo_pass'] )

# Your program should list the zoo names, habitat names and descriptions, and
# the animal names, descriptions and cuteness values.
display_section "Printing all the zoo names" do
  db["zoos"].find.each do |zoo|
     puts zoo["name"]
  end
end

display_section "Printing the habitat names from the habitats collection" do
  db["habitats"].find.each do |habitat|
    puts "#{habitat["name"]} : #{habitat["description"]}"
  end
end

display_section "Printing the animal names from the animals collection" do
  db["animals"].find.each do |animal|
    habitat = db["habitats"].find_one " _id" => animal["habitat_id"] 
    puts "#{animal["name"]}"
    20.times { print "-" }
    puts ""
    puts " Cuteness: #{animal["cuteness"]}" if animal["cuteness"]
    puts " Description: #{animal["description"]}" if animal["description"]
    puts " Habitat: #{habitat["name"]}" if habitat
    puts ""
  end
end


# In addition, you will ﬁnd that some animals are associated with particular
# habitats. Your list of animals should also display the habitats associated
# with each animal, if such a relationship exists. (How might you use the
# “object id’s” to display the associated habitats?) 

# Lastly, your program should prompt the user for an animal name, description,
# and cuteness value (an integer), and store a new animal in the database.
