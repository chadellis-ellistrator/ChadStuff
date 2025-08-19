from server import Server

class Client:
    server = Server()

    def create_user(self, name: str, password: str) -> None:
        self.name = name
        self.server.add_user(name, password)

    def login(self, password: str) -> bool:
        return self.server.login_user(self.name, password)