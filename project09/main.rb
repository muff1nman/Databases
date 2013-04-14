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
end


redis = Redis.new(
  host: "pub-redis-13059.us-west-1.1.azure.garantiadata.com",
  port: 13059,
  password: "wuwnosql403"
)

display_section "Testing a simple storage of a value" do
  puts redis.set "somevalue", 23

  puts redis.get "somevalue"

end

