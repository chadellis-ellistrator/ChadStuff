import uuid

from bank import Bank
from person import Person

class Account:
    owner: Person
    account_number: int
    bank: Bank
    id: uuid

    def __init__(self, owner: Person, bank: Bank, account_number: int):
        self.owner = owner
        self.bank = bank
        self.account_number = account_number
        self.id = uuid.uuid4()

    def __repr__(self):
        return f"{self.bank}, {self.account_number}, {self.owner}"