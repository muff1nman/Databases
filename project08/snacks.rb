#!/usr/bin/env ruby

require 'rubygems'
require 'active_record'
require 'logger'

#
# Configuration
#
ActiveRecord::Base.logger = Logger.new(STDERR) # Comment this line to turn off log output
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
  has_and_belongs_to_many :machines
  validates :calories, numericality: { greater_than_or_equal_to: 0 }

  def to_s
    name
  end
end

class Machine < ActiveRecord::Base
  has_and_belongs_to_many :snacks
  belongs_to :building

  def to_s
    "#{serial_number}, #{description}"
  end

end

class Building < ActiveRecord::Base
  has_many :machines

  def to_s
    name
  end

end

#
# Core functions.
#
def list_users( criteria=:all )
  users = User.find( criteria )
  users.each do |user|
    puts user
  end
end

def list_snacks( criteria=:all )
  snacks = Snack.find( criteria )
  snacks.each do |snack| 
    puts "Snack: #{snack}"
    snack.machines.each do |dispenser|
      puts "   Machine: #{dispenser}"
    end
  end
end

def list_machines( criteria=:all )
  machines = Machine.find( criteria )
  machines.each do |machine|
    puts "Machine: #{machine} (Building: #{machine.building})"
  end
end

def list_buildings( criteria=:all )
  buildings = Building.find( criteria )
  buildings.each do |building| 
    puts "Building: #{building} (#{building.machines.count} machines)"
  end
end

def find_snack( name )
  unless snack = Snack.find_by_name( name )
    puts "Not Found"
    return
  end
  snack.machines.each do |machine|
    puts "Machine: #{machine} (Building: #{machine.building})"
  end
end

def prompt( string )
  print string
  $stdin.gets.chomp
end

def add_snack
  snack = Snack.new
  snack.name = prompt "Name: "
  snack.description = prompt "Description: "
  snack.calories = prompt "Calories "
  unless snack.valid?
    puts "Could not save snack because: "
    snack.errors.full_messages.each do |error|
      puts "  #{error}"
    end
  else
    snack.save
    add_snack_to_machines( snack )
  end
end

def list_buildings_with_snacks
  Building.all.each do |building|
    puts building
    building.machines.each do |machine|
      puts machine
    end
  end
end

def add_snack_to_machines( snack )
  list_buildings_with_snacks
  machine = Machine.find_by_id( prompt "Enter a machine id" )
  unless machine
    puts "Cannot find a machine with that id"
    add_snack_to_machines( snack )
  else
    machine.snacks << snack
    puts "Snack added to machine"
  end
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
    find_snack( $stdin.gets.chomp )
  when "F"
    puts "\nAdding a new Snack"
    add_snack
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
