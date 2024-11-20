var express = require('express');
var router = express.Router();

var recomendacoesService = require('../microservices/recomendacoesService');

router.get('/pedidos/estilistas/:username',function(req,res,next){
    const username = req.params.username;
    recomendacoesService.getPedidosEstilista(username).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})


router.get('/pedidos/cliente/:username',function(req,res,next){
    const username = req.params.username;
    recomendacoesService.getPedidosCliente(username).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.post('/pedidos',function(req,res,next){
    const usernamecliente = req.body.usernameCliente;    
    const usernameestilista = req.body.usernameEstilista;    
    const estilos = req.body.estilos;
    const cores = req.body.cores;
    const nrOutfits = req.body.nrOutfits;
    const orcamento = req.body.orcamento;
    const peçasExcluidas = req.body.peçasExcluidas;
    const fabricsPreferences = req.body.fabricsPreferences;
    const occasions = req.body.occasions;

    recomendacoesService.insertPedido(usernamecliente,usernameestilista,
        estilos,cores,nrOutfits,orcamento,peçasExcluidas,fabricsPreferences,occasions
    ).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

router.put('/pedidoEdit',function(req,res,next){
    
    const descricao = req.body.descricao;
    const itemsEditType = req.body.itemsEditType; // "add", "remove"
    const conjunto = req.body.conjunto; //[{codigo,designacao,idLoja}]
    const status = req.body.status; // PENDING, PAYED, COMPLETE


    recomendacoesService.editPedido(descricao,itemsEditType,conjunto,status
    ).then(pedidos=>{
        res.jsonp(pedidos);
    }).catch(err=>{
        res.status(400).jsonp(err);
    })
})

module.exports = router;
