
from datetime import date

from account import Account
from bank import Bank
from net import Net
from netter import create_nets, settle_nets, submit_nets
from payout import Payout
from person import Person

schwab = Bank("Charles Schwab" , 11111111)
capone = Bank("Capital One", 22222222)
chad = Person("Chad", "Ellis")
karl = Person("Karl", "Ellis")
jack = Person("Jack", "Ellis")

chad_account = Account(chad, schwab, 123456789)
karl_account = Account(karl, bank=capone, account_number=234567890)
jack_account = Account(jack, bank=capone, account_number=234567891)

print(chad_account)
print(karl_account)
print(jack_account)

p1 = Payout(source=chad_account, target=karl_account, amount=200.25, target_date=date.today())
p2 = Payout(source=chad_account, target=jack_account, amount=100.01, target_date=date.today())
print(p1)
print(p2)

net = Net(chad_account)
net.add_payout(p1)
net.add_payout(p2)

print(net.sum())

p3 = Payout(source=karl_account, target=jack_account, amount=11.55, target_date=date.today())

print("============ CREATE ============")
nets = create_nets([p1, p2, p3])
for n in nets:
    print(n)

print("============ SUBMIT ============")
submit_nets(nets)
for n in nets:
    print(n)

print("============ SETTLE ============")
settle_nets(nets)
for n in nets:
    print(n)


