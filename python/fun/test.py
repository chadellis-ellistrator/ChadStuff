def get_exponents(num: int) -> int:
    exp = 0
    if num > 99:
        exp = exp + 3 * (num - 99)
    if num > 9:
        exp = exp + 2 * (num - 9)
    return exp + num - 1


n = 11
num = 0
exp = get_exponents(n)
for i in range(1, n+1):
    extra = 1 if n > 9 else 0
    if i > 9:
        extra = 0 
    num = num + (i * (10 ** (n-i+extra)))
print(num)
