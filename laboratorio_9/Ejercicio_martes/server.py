#!/usr/bin/python3

import socket
from tabulate import tabulate

sillas = list()

for i in range(5):
    sillas.append(list())
    for j in range(10):
        sillas[i].append("libre")

def ocupar_silla(x, y, apellido):
    sillas[x - 1][y - 1] = apellido

#ocupar_silla(1, 2, "Fernandez")
print(tabulate(sillas, tablefmt='fancy_grid'))

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_socket.bind(("127.0.0.1", 9091))

apellido = str()

while True:
    mensaje, client_pair = server_socket.recvfrom(1024)

    if(mensaje.decode() == ""):
        server_socket.sendto(tabulate(sillas, tablefmt='fancy_grid').encode(), client_pair)
        apellido = ""
    elif(mensaje.decode()[0] == "A"):
        apellido = mensaje.decode().split(':')[1]
    elif(mensaje.decode()[0] == "P"):
        coordenadas = mensaje.decode().split(':')[1].split(';')
        if(sillas[int(coordenadas[0]) - 1][int(coordenadas[1]) - 1] == "libre"):
            server_socket.sendto("no ocupado".encode(), client_pair)
            sillas[int(coordenadas[0]) - 1][int(coordenadas[1]) - 1] = apellido
        else:
            server_socket.sendto("Asiento Ocupado elige otro".encode(), client_pair)
        print(tabulate(sillas, tablefmt='fancy_grid'))
