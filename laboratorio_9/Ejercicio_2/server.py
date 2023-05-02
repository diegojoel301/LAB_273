#!/usr/bin/python3

import socket

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_socket.bind(("127.0.0.1", 9091))

server_socket.listen(5)

while True:

    conexion, client_address = server_socket.accept()

    data = conexion.recv(1024)

    file_index = open("index.html", "r")

    message_response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" + str(file_index.read())

    print(message_response)

    file_index.close()

    conexion.sendall(message_response.encode())
    conexion.close()