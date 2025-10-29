from datetime import date, datetime

from account import Account
from payout_state import PayoutState

class Payout:
    amount: float
    target_date: date
    target: Account
    source: Account
    state: PayoutState
    state_datetime: datetime

    def __init__(self, source: Account, target: Account, amount: float, target_date: date):
        self.source = source
        self.target = target
        self.amount = amount
        self.target_date = target_date
        self.state = PayoutState.CREATED
        self.state_datetime = datetime.now()
    
    def submit(self, ts: datetime = None):
        self.state = PayoutState.SUBMITTED
        self.state_datetime = ts if ts else datetime.now()

    def settle(self, ts: datetime = None):
        self.state = PayoutState.SETTLED
        self.state_datetime = ts if ts else datetime.now()

    def __repr__(self):
        return f"Source: {self.source.id}, Target: {self.target.id}, Amount {self.amount}, Target Date: {self.target_date}"
    