var express = require('express');
var router = express.Router();
const favoritosService = require('../microservices/favoritosService')


/*Obter itens por genero*/
router.get("/genero/:username",function(req,res,next){
  const username = req.params.username;
  const gender = req.query.gender;
  const page = req.query.page;
  const number = req.query.number;
  favoritosService.getPerGenderItems(username, gender, page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(err.error.status).jsonp(err);
  })
})


/*Obter itens por preco*/
router.get("/price/:username",function(req,res,next){
  const username = req.params.username;
  const page = req.query.page;
  const number = req.query.number;
  const min = req.query.min;
  const max = req.query.max;
  favoritosService.getPerPriceItems(username, min,max,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(err.error.status).jsonp(err);
  })
})


/*Obter itens por tamanho*/
router.get("/size/:username",function(req,res,next){
  const username = req.params.username;
  const size = req.query.size;
  const page = req.query.page;
  const number = req.query.number;
  favoritosService.getPerSizeItems(username, size, page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(err.error.status).jsonp(err);
  })
})


/*Adicionar item aos favoritos do user*/
router.post("/addItem", function(req, res, next) {

  const codigo = req.body.codigoItem;
  const idloja = req.body.idLoja;
  const username = req.body.clienteUsername;
  const designacao = req.body.designacao;
  const preco = req.body.preco;
  const disponibilidade = req.body.disponibilidade;
  const tipo = req.body.tipo;
  const imagem = req.body.imagem;
  const subclasse = req.body.subclasse;
  const dimensao = req.body.dimensao;
  const identificador = req.body.identificador;

  favoritosService.addItemFavorite(codigo, idloja,username, designacao,
    preco, disponibilidade, tipo, imagem, subclasse, dimensao,identificador).then(resp => {
      res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});  



/*Remover item*/
router.delete("/removeItem",function(req,res,next){

  const username = req.body.username;
  const itemCode = req.body.itemCode;
  const idLoja = req.body.idLoja;

  favoritosService.removeItem(username,itemCode,idLoja).then(resp=>{
    res.jsonp(resp);
  }).catch(err=>{
    res.status(err.error.status).jsonp(err);
  })
});

/*Get Items By Page*/
router.get("/:username", function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  const username = req.params.username;
  favoritosService.getUserFavsByPage(username,page, number).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

module.exports = router;
