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

    console.log(notifications.get(username));
    if (clients.has(username)) {
        const res = clients.get(username);
        res.write(`notificacao: ${JSON.stringify(req.body)}\n\n`); 
        let notificacoes = notifications.get(username);
        notificacoes.pop()
        notifications.set(username,notificacoes);
    }

    res.status(200).jsonp({"msg":"insertion done"});
})


router.get('/notifications/retrieve/:username', (req, res) => {
    const username = req.params.username;
    res.setHeader('Content-Type', 'text/event-stream');
    res.setHeader('Cache-Control', 'no-cache');
    res.setHeader('Connection', 'keep-alive');
    res.flushHeaders();

    if(notifications.get(username)){
        notifications.get(username).forEach(notificacao => {            
            res.write(`notificacao: ${JSON.stringify(notificacao)}\n\n`)
        });
        notifications.delete(username)
    }else{
        res.write(``)
    }

    clients.set(username, res);

    req.on('close', () => {
        clients.delete(username);
    });
});

router.post('/addInterested', validateToken.authenticateToken, async function(req,res,next){
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

module.exports = router;
