var express = require('express');
var router = express.Router();
const catalogoService = require('../microservices/catalogoService')
const validate = require('../middleware/index')

/*Get Items By Page*/
router.get("/", function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getItemsByPage(page, number).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});



/*Get Items By Name*/
router.get("/allitems", function(req, res, next) {
  const designacao = req.query.designacao;
  const page = req.query.page;
  const number = req.query.number;

  catalogoService.getItemsByName(designacao,page,number).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});


/*Get All Items*/
router.get("/all", function(req, res, next) {
  catalogoService.getAllItems().then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});

/*Get random items for home page*/
router.get('/random',function(req,res,next){
  catalogoService.getRandomItems().then(items=>{
    res.jsonp(items);
  }).catch(error=>{
    res.status(404).jsonp(error);
  });
});

/*Show Details from an Item Page*/
router.get("/items/:id", function(req, res, next) {
  catalogoService.showItemDetails(req.params.id).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});

/*Get Reviews from a Item id with pagination*/
router.get("/items/:id/reviews", function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getReviews(req.params.id,page,number).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});  

/*Adicionar Review*/
router.post("/items/:id/addreview",validate.verifyToken, function(req, res, next) {
  const username = req.body.username;
  const profileImg = req.body.profileImg;
  const texto = req.body.texto;
  const timestamp = req.body.timestamp;
  const rating = req.body.rating;
  catalogoService.adicionaReview(req.params.id,username,profileImg,texto,timestamp,rating
  ).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(403).jsonp(err);
  });
});  

/*Remover Review*/
router.delete("/items/:id/delreview/:username",validate.verifyToken,function(req,res,next){
  const id = req.params.id;
  const username = req.params.username;
  catalogoService.removeReview(id,username).then(resp=>{
    res.jsonp(resp);
  }).catch(err=>{
    res.status(403).jsonp(err);
  })
});

/*Obter itens de uma loja*/
router.get("/lojas/:lojaid",function(req,res,next){
  const lojaid = req.params.lojaid;
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getItemsByShop(lojaid,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(404).jsonp(err);
  })
})

/*Obter itens por tipo*/
router.get("/type/:type",function(req,res,next){
  const type = req.params.type;
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getItemsByType(type,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
})


/*Obter itens por tipo e preco*/
router.get("/type/:type/price",function(req,res,next){
  const type = req.params.type;
  const page = req.query.page;
  const number = req.query.number;
  const min = req.query.min;
  const max = req.query.max;
  catalogoService.getPerPriceandTypeItems(type,min,max,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
})

/*Obter itens por tipo, preco e nome*/
router.get("/type/:type/price/:name",function(req,res,next){
  const name = req.params.name;
  const type = req.params.type;
  const page = req.query.page;
  const number = req.query.number;
  const min = req.query.min;
  const max = req.query.max;
  catalogoService.getPerPriceTypeNameItems(type,name,min,max,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
})


/*Obter itens por preco*/
router.get("/price",function(req,res,next){
  const page = req.query.page;
  const number = req.query.number;
  const min = req.query.min;
  const max = req.query.max;
  catalogoService.getPerPriceItems(min,max,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
})

/*Obter itens por preco e nome*/
router.get("/price/:name",function(req,res,next){
  const name = req.params.name;
  const page = req.query.page;
  const number = req.query.number;
  const min = req.query.min;
  const max = req.query.max;
  catalogoService.getPerPriceNameItems(name,min,max,page,number).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
})

/* Libertar recursos reservados de uma encomenda cancelada. */
router.post("/freeItems", function(req, res, next) {
  const items = req.body;
  console.log(items);
  catalogoService.freeItems(items).then(resp => {
      res.jsonp(resp);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});  


/*Adicionar item Peça*/
router.post("/addItem/Peca", function(req, res, next) {
  const codigo = req.body.codigo;
  const designacao = req.body.designacao;
  const preco = req.body.preco;
  const estilo = req.body.estilo;
  const cores = req.body.cores;
  const tamanho = req.body.tamanho;
  const tipo = req.body.tipo;
  const disponibilidade = req.body.disponibilidade;
  const imagem = req.body.imagem;
  const idLoja = req.body.idLoja;
  const nrdisponiveis = req.body.nrdisponiveis;
  catalogoService.adicionaPeca(codigo,designacao,
    preco,estilo,cores,tamanho,tipo,disponibilidade,imagem,
    idLoja,nrdisponiveis).then(resp => {
      res.jsonp(resp);
  }).catch(err => {
    res.status(400).jsonp(err);
  });
});  


/*Adicionar item Set*/
router.post("/addItem/Set", function(req, res, next) {
  const codigo = req.body.codigo;
  const designacao = req.body.designacao;
  const preco = req.body.preco;
  const estilo = req.body.estilo;
  const tamanho = req.body.tamanho;
  const tipo = req.body.tipo;
  const disponibilidade = req.body.disponibilidade;
  const imagem = req.body.imagem;
  const idLoja = req.body.idLoja;
  const codigoPecas = req.body.codigoPecas;
  catalogoService.adicionaSet(codigo,designacao,
    preco,estilo,tamanho,tipo,disponibilidade,imagem,
    idLoja,codigoPecas).then(resp => {
      res.jsonp(resp);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});


/*Adicionar item Calcado*/
router.post("/addItem/Calcado", function(req, res, next) {
  const codigo = req.body.codigo;
  const designacao = req.body.designacao;
  const preco = req.body.preco;
  const estilo = req.body.estilo;
  const cores = req.body.cores;
  const tamanho = req.body.tamanho;
  const tipo = req.body.tipo;
  const disponibilidade = req.body.disponibilidade;
  const imagem = req.body.imagem;
  const idLoja = req.body.idLoja;
  const nrdisponiveis = req.body.nrdisponiveis;
  catalogoService.adicionaCalcado(codigo,designacao,
    preco,estilo,cores,tamanho,tipo,disponibilidade,imagem,
    idLoja,nrdisponiveis).then(resp => {
      res.jsonp(resp);
  }).catch(err => {
    res.status(400).jsonp(err);
  });
});

/*Remover item*/
router.delete("/deleteItem/:itemID",validate.verifyToken,function(req,res,next){
  catalogoService.removeItem(req.params.itemID).then(resp=>{
    res.jsonp(resp);
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
});

/*Obter os 4 itens com mais aquicoes de uma loja*/
router.get("/trending/:lojaid",function(req,res,next){
  const lojaid = req.params.lojaid;
  catalogoService.getTrendingItems(lojaid).then(items=>{
    res.jsonp(items)
  }).catch(err=>{
    res.status(400).jsonp(err);
  })
})


module.exports = router;
