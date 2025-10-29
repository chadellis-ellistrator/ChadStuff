from typing import List
from datetime import datetime

from account import Account
from payout_state import PayoutState
from payout import Payout

class Net:
    source: Account
    payouts: List[Payout]
    state: PayoutState
    state_datetime: datetime

    def __init__(self, source: Account):
        self.source = source
        self.payouts = []
        self.state = PayoutState.CREATED
        self.state_datetime = datetime.now()
    
    def add_payout(self, payout: Payout) -> None:
        self.payouts.append(payout)
    
    def sum(self) -> float:
        sum = 0
        for p in self.payouts:
            sum += p.amount
        return sum
    
    def submit(self) -> None:
        self.state = PayoutState.SUBMITTED
        self.state_datetime = datetime.now()
        for p in self.payouts:
            p.submit(ts = self.state_datetime)

    def settle(self) -> None:
        self.state = PayoutState.SETTLED
        self.state_datetime = datetime.now()
        for p in self.payouts:
            p.settle(ts = self.state_datetime)
    
    def __repr__(self):
        l = []
        l.append(f"Source: {self.source.owner}, Sum: {self.sum()}, State: {self.state}")
        for p in self.payouts:
            l.append(f"\n -- Target {p.target.owner}, Amount: {p.amount}, State: {p.state}")
        return ''.join(l)
