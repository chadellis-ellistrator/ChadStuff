class User:
    def __init__(self, name: str, password: str):
        self.name = name
        self.password = password
        self.loggedIn = False

    def __repr__(self):
        return f"{{name: '{self.name}', loggedIn: {self.loggedIn}}}"


    def authenticate(self, password: str) -> bool:
        loggedIn = False
        if self.password == password:
            loggedIn = True
        return loggedIn
