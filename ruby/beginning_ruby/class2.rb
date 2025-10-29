class Person
attr_accessor :name, :age
end

person1 = Person.new
person1.name = "Chad"
puts person1.name

class PersonCount
  @@count = 0

  def initialize
    @@count += 1
  end

  def self.count
    @@count
  end
end

PersonCount.new
PersonCount.new
PersonCount.new
puts PersonCount.count
