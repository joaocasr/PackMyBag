var express = require('express')
var router = express.Router();
var utilizadoresService = require('../microservices/utilizadoresService')
const validate = require('../middleware/index')


router.post('/signin', async function(req,res,next){
    const username = req.body.username;
    const password = req.body.password;
    try {
        const resp = await utilizadoresService.signIn(username, password);
        //console.log(resp.response);
        if(resp.response) res.status(resp.response.status).jsonp(resp);
        else res.jsonp(resp);
    } catch (err) {
        //console.log(err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.post('/signup/user', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const genero = req.body.genero;
    const morada = req.body.morada;
    const nrTelemovel= req.body.nrTelemovel;

    try {
        const resp = await utilizadoresService.signUpUser(username, nome, password, email,genero,morada,nrTelemovel);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/signup/estilista', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const genero= req.body.genero;
    const bio = req.body.bio;

    try {
        const resp = await utilizadoresService.signUpEstilista(username, nome, password, email,genero,bio);
        console.log(resp);
        res.jsonp(resp);
    } catch (err) {
        console.log(err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/signup/tecnico', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const nomeLoja = req.body.nomeLoja;
    try {
        const resp = await utilizadoresService.signUpTecnico(username, nome, password, email,nomeLoja);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.get('/estilistas',validate.verifyToken, async function(req,res,next){
    try {
        const page = req.query.page;
        const number = req.query.number;    
        const resp = await utilizadoresService.getEstilistas(req.token,page,number);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.get('/profileImg/:username', async function(req,res,next){
    try {
        const username = req.params.username;
        const resp = await utilizadoresService.getProfileImg(username);
        res.set('Content-Type', resp.headers['content-type']);
        res.send(resp.data); 
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


module.exports = router;
