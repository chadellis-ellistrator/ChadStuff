puts 10
puts 10.class

i = 0
loop do
  i += 1
  puts i
  break if i >= 10
end

i = 0
while (i < 15)
  i += 1
  next if i % 2 == 0
  puts i
end

puts "this is a test".reverse.upcase.split(' ').reverse.join('*')
