#!/usr/bin/env ruby

require 'redis'

redis = Redis.new(
  host: "pub-redis-13059.us-west-1.1.azure.garantiadata.com",
  port: 13059,
  password: "wuwnosql403"
)

puts redis.info
