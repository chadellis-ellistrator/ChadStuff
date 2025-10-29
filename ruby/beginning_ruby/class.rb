class Person
  def initialize(name, age)
    @name = name
    @age = age
  end

  def name
    @name
  end

  def name=(new_name)
    @name = new_name
  end

  def age
    return @age
  end
end

person1 = Person.new('Chad', 45)
person2 = Person.new('Karl', 21)
puts person1.name
puts person2.age
person1.name = "Fred"
puts person1.name 
