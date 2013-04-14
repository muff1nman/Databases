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
  redis.hset "somehash", "age", "21"
  puts redis.hget "somehash", "name"
end


