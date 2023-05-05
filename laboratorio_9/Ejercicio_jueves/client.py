#!/usr/bin/python3

import socket

client_socket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

while True:

    apellido = input("[+] Introduce tu apellido: ")

    fecha = input("[+] Introduce la fecha (Ej. 04-04-2023): ")

    hora_entrada = input("[+] Introduce la hora de entrada (Ej. 8:30): ")

    hora_salida = input("[+] Introduce la hora de salida (Ej. 8:30): ")

    msg = fecha + ";" + hora_entrada + ";" + hora_salida + ";" + apellido

    client_socket.sendto(msg.encode(), ('127.0.0.1', 9091))

    msg, server_pair = client_socket.recvfrom(1024)

    print(msg.decode())

 