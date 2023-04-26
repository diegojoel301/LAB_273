const dgram = require('dgram');
const client = dgram.createSocket('udp4');
const prompt = require("prompt-sync")({ sigint: true });
const readline = require('readline');

const read_line = readline.createInterface(
    {
        input: process.stdin,
        output: process.stdout
    }
);

read_line.setPrompt("[!] Introduce mensaje: ");

read_line.prompt();
read_line.on('line', (message, info) => {

    client.send(message, 8081, 'localhost', (err) => {
        if(err)
        {
            console.error("Hubo un error...");
        }
    });

})


    

client.on('message', (message, info) => {

    console.log(message.toString());

});
