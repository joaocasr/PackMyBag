var express = require('express')
var router = express.Router()
var notificacoesService = require('../microservices/notificacoesService');
var validateToken = require('../middleware/index')

var notifications = new Map()
var clients = new Map();  

router.post('/getnotifications/:username', function(req,res,next){

    const username = req.params.username

    if(notifications.get(username)) {
        notifications.get(username).push(req.body);
    }else{
        notifications.set(username,[req.body])
    }
    const newNotification = req.body;


    console.log(notifications.get(username));
    if (clients.has(username)) {
        const res = clients.get(username);
        res.write(`data: ${JSON.stringify(newNotification)}\n\n`);
    }else{
        if(notifications.get(username)) {
            notifications.get(username).push(req.body);
        }else{
            notifications.set(username,[req.body])
        }
    }

    res.status(200).jsonp({"msg":"insertion done"});
})


router.get('/notifications/retrieve/:username', (req, res) => {
    const username = req.params.username;
    res.setHeader('Content-Type', 'text/event-stream');
    res.setHeader('Cache-Control', 'no-cache');
    res.setHeader('Connection', 'keep-alive');
    res.setHeader('Transfer-Encoding', 'identity');
    res.flushHeaders();

    console.log("Checking notifications for client: " + username);
    const userNotifications = notifications.get(username);

    if (userNotifications && userNotifications.length > 0) {
        userNotifications.forEach(notificacao => {
            console.log("Sending notification to client...");
            console.log(notificacao);
            res.write(`data: ${JSON.stringify(notificacao)}\n\n`);
        });
        notifications.set(username,[])

    } else {
        res.write(`:\n\n`);
    }

    clients.set(username, res);

    req.on('close', () => {
        console.log("Connection closed for client: " + username);
        clients.delete(username);
    });
});


router.post('/addInterested', validateToken.verifyToken, async function(req,res,next){
    const codigo = req.body.codigo;
    const designacao = req.body.designacao;
	const disponibilidade = req.body.disponibilidade;
	const idLoja = req.body.idLoja.toString();
	const username= req.body.username;
	const nomeuser=req.body.nomeuser;
	const email= req.body.email;
    try {
        const resp = await notificacoesService.addInterested(codigo,designacao,disponibilidade,idLoja,username,nomeuser,email);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err);
    }
})

router.get('/getmyNotifications/:username/:page/:number', async function(req,res,next){
    const username = req.params.username;
    const page = req.query.page;
    const number = req.query.number;
    try {
        const resp = await notificacoesService.getMyNotifications(username,page,number);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err);
    }
});

router.delete('/removeMyNotification/:username/:id', async function(req,res,next){
    const username = req.params.username;
    const id = req.params.id;
    try {
        const resp = await notificacoesService.removeNotificationFromClientByID(username,id);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err);
    }
});

router.delete('/clearNotifications/:username', async function(req,res,next){
    const username = req.params.username;
    try {
        const resp = await notificacoesService.clearNotifications(username);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err);
    }
});


module.exports = router;
