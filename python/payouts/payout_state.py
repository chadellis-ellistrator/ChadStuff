from enum import Enum


class PayoutState(Enum):
    CREATED = 1
    SUBMITTED = 2
    SETTLED = 3
    