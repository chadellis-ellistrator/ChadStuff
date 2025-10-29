class Bank:
    name: str
    routing_number: int

    def __init__(self, name: str, routing_number: int):
        self.name = name
        self.routing_number = routing_number
    
    def __repr__(self):
        return f"{self.name}: {self.routing_number}"
