var express = require('express');
var router = express.Router();

var recomendacoesService = require('../microservices/recomendacoesService');

router.get('/pedidos/estilistas/:username',function(req,res,next){
    const username = req.params.username;
    const page = req.query.page;
    const number = req.query.number;
    recomendacoesService.getPedidosEstilista(username,page,number).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.get('/pedidosEinfo/:username',function(req,res,next){
    const username = req.params.username;
    recomendacoesService.getPedidosEstilistaInfo(username).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})


router.get('/pedidos/cliente/:username',function(req,res,next){
    const username = req.params.username;
    const page = req.query.page;
    const number = req.query.number;
    recomendacoesService.getPedidosCliente(username,page,number).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.post('/removePedido',function(req,res,next){
    const codigo = req.body.codigo;

    recomendacoesService.removePedido(codigo).then(pedido=>{
        res.jsonp(pedido);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.post('/pedidos',function(req,res,next){
    const usernamecliente = req.body.usernameCliente;    
    const usernameestilista = req.body.usernameEstilista;    
    const nome = req.body.nome;
    const estilos = req.body.estilos;
    const cores = req.body.cores;
    const nrOutfits = req.body.nrOutfits;
    const orcamento = req.body.orcamento;
    const peçasExcluidas = req.body.peçasExcluidas;
    const fabricsPreferences = req.body.fabricsPreferences;
    const occasions = req.body.occasions;

    recomendacoesService.insertPedido(usernamecliente,usernameestilista,nome,
        estilos,cores,nrOutfits,orcamento,peçasExcluidas,fabricsPreferences,occasions
    ).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.put('/addItem',async function(req,res,next){
    
    const nome = req.body.nome;
    const item = req.body.item;

    try{
        let r = await recomendacoesService.addItemToPedido(nome,item)
        res.jsonp(r);
    }catch(err){
        res.status(400).jsonp(err);
    }
})

router.delete('/removeItem',async function(req,res,next){
    
    const nome = req.body.nome;
    const item = req.body.item;

    try{
        let r = await recomendacoesService.removeItemFromPedido(nome,item)
        res.jsonp(r);
    }catch(err){
        res.status(400).jsonp(err);
    }
})

router.put('/pedidosEditDescricaoOrComplete',function(req,res,next){
    console.log("hey")
    const nome = req.body.nome;
    const descricao = req.body.descricao;
    const status = req.body.status; // pending, PAYED, completed


    recomendacoesService.editDescricaoOrCompletePedido(nome, descricao, status
    ).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.put('/pedidos/changeStatus',function(req,res,next){
    const nome = req.body.nome;
    const status = req.body.status;// pending, PAYED, completed

    recomendacoesService.changeStatus(nome,status).then(pedido=>{
        res.jsonp(pedido);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

module.exports = router;
