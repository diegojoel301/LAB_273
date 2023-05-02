#!/usr/bin/python3

import socket

def f(x, y):

    flag = (x < 0 and y < 0) or (x >= 0 and y >= 0)

    if x < 0:
        x = -x
    if y < 0:
        y = -y
        
    res = 0

    while x != 0:
        res += y
        x -= 1
    
    if flag:
        return res
    return -res

localIP     = "127.0.0.1"

localPort   = 9091

server_socket = socket.socket(socket.AF_INET, type=socket.SOCK_DGRAM)

server_socket.bind((localIP, localPort))

while(True):

    mensaje, client_pair = server_socket.recvfrom(1024)

    res = "Resultado de {} es: ".format(mensaje.decode().strip()) + str(f(int(mensaje.decode().split('*')[0]), int(mensaje.decode().split('*')[1]))) + "\n"

    print(res)

    server_socket.sendto(res.encode(), client_pair)

   
