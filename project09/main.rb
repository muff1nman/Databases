#!/usr/bin/env ruby

require 'redis'
require 'json'

def display_divider
  20.times { print "=" }
  puts ""
end

def display_section( string )
  puts string
  display_divider
  yield
  display_divider
  puts "\n\n"
end


redis = Redis.new(
  host: "pub-redis-13059.us-west-1.1.azure.garantiadata.com",
  port: 13059,
  password: "wuwnosql403"
)

display_section "Testing a simple storage of a value" do
  redis.set "somevalue", 23
  puts redis.get "somevalue"
end

display_section "Testing a simple storage of a list" do
  redis.del "somelist"
  redis.lpush "somelist", "2"
  redis.lpush "somelist", "anothervalue"
  puts "length: #{redis.llen "somelist" }"
  puts "elements: #{redis.lrange "somelist", 0, -1}"
end

display_section "Testing a simple storage of a hash" do
  redis.del "somehash"
  redis.hset "somehash", "name", "billy"
  puts "Exists? #{redis.hexists "somehash", "name"}"
  redis.hset "somehash", "age", "21"
  puts redis.hget "somehash", "name"
  puts redis.hget "somehash", "age"
  puts "Incrementing age"
  puts redis.hincrby "somehash", "age", 1
  puts "Number of fields: #{redis.hlen "somehash"}"
end

display_section "Testing sorted sets" do
  redis.del "someset"
  puts "Adding to A #{redis.zadd "someset", 2, "lion"}"
  puts "Adding to A #{redis.zadd "someset", 1, "bear"}"
  puts "Adding to A #{redis.zadd "someset", 10, "bear"}"
  puts "Displaying A"
  puts redis.zrange "someset", 0, -1
  puts "Count below 10"
  puts redis.zcount "someset", 0, 10
  puts "Removing"
  puts redis.zrem "someset", "lion"
  puts "Displaying A"
  puts redis.zrange "someset", 0, -1
end

display_section "Testing string operations" do
  redis.set "string", "hello"
  puts redis.get "string"
  puts "Appending"
  puts redis.append "string", " world"
  puts redis.get "string"
  puts "Get range"
  puts redis.getrange "string", 1, 4
  puts "Get length"
  puts redis.strlen "string"
end

