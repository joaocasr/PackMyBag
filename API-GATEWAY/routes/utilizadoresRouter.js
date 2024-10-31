var express = require('express')
var router = express.Router();
var utilizadoresService = require('../microservices/utilizadoresService')


router.post('/signup/user', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    try {
        const resp = await utilizadoresService.signUpUser(username, nome, password, email);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.post('/signin', async function(req,res,next){
    const username = req.body.username;
    const password = req.body.password;
    try {
        const resp = await utilizadoresService.signIn(username, password);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


module.exports = router;
