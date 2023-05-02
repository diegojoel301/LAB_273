#!/usr/bin/python3

import socket
 

client_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

while True:
    msg = "%s*%s" % (input("[+] Introduce primer numero: "), input("Introduce segundo numero: "))

    client_socket.sendto(msg.encode(), ('127.0.0.1', 9091))

    msg, server_pair = client_socket.recvfrom(1024)

    print(msg.decode())