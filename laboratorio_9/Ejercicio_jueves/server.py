import socket
import datetime

emp_dic = dict()

def add_emp(fecha, hora_entrada, hora_salida, apellido):
    hora_entrada_old = hora_entrada
    hora_salida_old = hora_salida

    hora_entrada = hora_entrada.split(':')
    hora_entrada = int(hora_entrada[0] + hora_entrada[1])

    hora_salida = hora_salida.split(':')
    hora_salida = int(hora_salida[0] + hora_salida[1])

    day = datetime.datetime.strptime(fecha, '%d-%m-%Y').strftime('%A')

    if len(list(emp_dic)) == 40:
        return "Limite de empleados..\n"

    if apellido in list(emp_dic.keys()):
        return "Este empleado ya fue registrado anteriormente\n"

    print(day)    

    if not (day == "Monday" or day == "Tuesday" or day == "Wednesday" or day == "Thursday" or day == "Friday"):
        return "No esta en semana laboral\n"
    if not (hora_entrada >= 800 and hora_entrada <= 830):
        return "Tu hora de entrada no corresponde\n"
    if not (hora_salida >= 1200 and hora_salida <= 1230):
        return "Tu hora de salida no corresponde\n"
    
    fecha_hora = "Fecha: " + fecha + ", Hora Entrada: " + hora_entrada_old + ", Hora Salida: " + hora_salida_old
    
    emp_dic[apellido] = fecha_hora

    return "Exito al Registrar\n"        
"""
msg = '08-05-2023;08:12;12:14;Padilla'

fecha = msg.split(';')[0]

hora_entrada = msg.split(';')[1]

hora_salida = msg.split(';')[2]

apellido = msg.split(';')[3]

print(add_emp(fecha, hora_entrada, hora_salida, apellido))

print(emp_dic)
"""

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_socket.bind(("127.0.0.1", 9091))

apellido = str()

while True:
    mensaje, client_pair = server_socket.recvfrom(1024)

    msg = mensaje.decode()

    fecha = msg.split(';')[0]

    hora_entrada = msg.split(';')[1]

    hora_salida = msg.split(';')[2]

    apellido = msg.split(';')[3].strip()

    msg_response = add_emp(fecha, hora_entrada, hora_salida, apellido)

    server_socket.sendto(msg_response.encode(), client_pair)

    print(emp_dic)
    
