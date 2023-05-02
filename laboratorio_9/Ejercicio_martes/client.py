#!/usr/bin/python3

import socket

client_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

while True:
    
    client_socket.sendto("".encode(), ('127.0.0.1', 9091))

    msg, server_pair = client_socket.recvfrom(3072)

    print(msg.decode())

    apellido = input("Cual es tu apellido?: ")

    msg_send = "A:" + apellido

    client_socket.sendto(msg_send.encode(), ('127.0.0.1', 9091))

    while True:
        confirmacion = input("[+] Desea reservar asiento?(Y/N): ")
        if confirmacion.lower() == "y":
            msg_send = "P:" + input("[+] En que fila: ") + ";" + input("[+] En que columna: ")
            client_socket.sendto(msg_send.encode(), ('127.0.0.1', 9091))

            msg, server_pair = client_socket.recvfrom(1024)

            if msg.decode() == "Asiento Ocupado elige otro":
                print(msg.decode())
        else:
            break