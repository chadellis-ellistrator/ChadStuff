import uuid


class Person:
    first_name: str
    last_name: str
    id: uuid

    def __init__(self, first_name: str, last_name: str):
        self.first_name = first_name
        self.last_name = last_name
        self.uuid = uuid.uuid4()

    def __repr__(self):
        return f"{self.first_name} {self.last_name}"
