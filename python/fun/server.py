
from user import User


class Server:
    users = {}

    def add_user(self, name: str, password: str) -> None:
        if self.users.get(name) is None:
            self.users[name] = User(name, password)
    
    def login_user(self, name: str, password: str) -> bool:
        if self.users.get(name) is None:
            raise ValueError(f"User {name} does not exist")
        return self.users[name].authenticate(password)
    
