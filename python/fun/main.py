
from client import Client

client1 = Client()
client1.create_user("ellistrator", "foo")
client1.login("bar")

client2 = Client()
client2.create_user("scooby", "doo")
client2.login("doo")
print(client2.server.users)


