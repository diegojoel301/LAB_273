const dgram = require('dgram');
const server = dgram.createSocket('udp4');

/*server.on('listening', (msg, rinfo) => {
    console.log(`Server got: ${msg} from ${rinfo.address}:${rinfo.port}`);
});*/

server.on('message', (message, info) =>{

    var datetime = new Date();

    data = `[+] Fecha: ${datetime}\n[+] Direccion IP Cliente: ${info.address}\n[+] Puerto Cliente UDP: ${info.port}\n`

    const response = Buffer.from(data);

    console.log(data);


    server.send(response, info.port, info.address, (err) => {
        if(err)
        {
            console.error('Error al enviar respuesta');
        }
        else
        {
            console.log('Respuesta enviada exitosamente');
        }
    })

});

server.bind(8081);
