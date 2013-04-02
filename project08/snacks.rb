#!/usr/bin/env ruby

require 'rubygems'
require 'active_record'
require 'logger'

#
# Configuration
#
ActiveRecord::Base.logger = Logger.new(STDOUT) # Comment this line to turn off log output
ActiveRecord::Base.establish_connection(
  :host => 'csci403.c99q7trvwetr.us-west-2.rds.amazonaws.com',
  :username => $stdin.gets.chomp,
  :password => $stdin.gets.chomp,
  :adapter => 'mysql2',
  :database => 'snacks'
)

#
# Class definitions
#
class User < ActiveRecord::Base
  has_and_belongs_to_many :snacks

  def to_s
    name
  end

end

class  Snack < ActiveRecord::Base
  has_and_belongs_to_many :users

  def to_s
    name
  end
end

class Machine < ActiveRecord::Base
  has_many :snacks

  def to_s
    description
  end

end

class Building < ActiveRecord::Base

  def to_s
    name
  end

end

#
# Core functions.
#
def list_users
  users = User.all
  users.each do |user|
    puts user
  end
end

def list_snacks
  snacks = Snack.all
  snacks.each do |snack| 
    puts snack
  end
end

def list_machines
  machines = Machine.all
  machines.each { |machine| puts machine }
end

def list_buildings
  buildings = Building.all
  buildings.each { |building| puts building }
end


def main_menu
  puts "\nMain Menu."
  puts "A. List Buildings"
  puts "B. List Machines"
  puts "C. List Snacks"
  puts "D. List Users"
  puts "E. Find a Snack"
  puts "F. Add a New Snack"
  puts "Q. Quit"
end

def execute_command(command)
  case command
  when "A"
    puts "\nListing Buildings"
    list_buildings
  when "B"
    puts "\nListing Machines"
    list_machines
  when "C"
    puts "\nListing Snacks"
    list_snacks
  when "D"
    puts "\nListing Users"
    list_users
  when "E"
    puts "\nFind a Snack"
    # TODO find_snack
  when "F"
    puts "\nAdding a new Snack"
    # TODO add_snack
  when "Q"
    puts "Quitting... buh-bye."
  else
    puts "Sorry, I don't know how to do that. Too bad so sad."
  end
end

command = nil
puts "Snack Attack. Whee!"
while (command != 'Q')
  main_menu
  execute_command(command = gets.chomp!)
end
