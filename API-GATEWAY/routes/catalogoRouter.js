var express = require('express');
var router = express.Router();
const catalogoService = require('../microservices/catalogoService')

/*Get Items By Page*/
router.get("/", function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getItemsByPage(page, number).then(items => {
      res.jsonp(items);
  }).catch(err => {
      res.status(err.status).jsonp({ error: err.error });
  });
});

/*Get All Items*/
router.get("/all", function(req, res, next) {
  catalogoService.getAllItems().then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.status).jsonp({ error: err.error });
  });
});

/*Show Details from an Item Page*/
router.get("/items/:id", function(req, res, next) {
  catalogoService.showItemDetails(req.params.id).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.status).jsonp({ error: err.message });
  });
});

/*Get Reviews from a Item id with pagination*/
router.get("/items/:id/reviews", function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getReviews(req.params.id,page,number).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.status).jsonp({ error: err.message });
  });
});  

/*Adicionar Review*/
router.post("/items/:id/addreview", function(req, res, next) {
  const username = req.body.username;
  const nome = req.body.name;
  const profileImg = req.body.profileImg;
  const texto = req.body.texto;
  const timestamp = req.body.timestamp;
  const rating = req.body.rating;
  catalogoService.adicionaReview(req.params.id,username,
    nome,profileImg,texto,timestamp,rating
  ).then(items => {
      res.jsonp(items);
  }).catch(err => {
      res.status(err.status).jsonp({ error: err.message });
  });
});  

/*Remover Review*/
router.delete("/items/:id/delreview/:username",function(req,res,next){
  const id = req.params.id;
  const username = req.params.username;
  catalogoService.removeReview(id,username).then(resp=>{
    res.jsonp(resp);
  }).catch(err=>{
    res.status(err.status).jsonp({ error: err.message });
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
    res.status(err.status).jsonp({error:err.message});
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
    res.status(err.status).jsonp({error:err.message});
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
    res.status(err.status).jsonp({error:err.message});
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
    res.status(err.status).jsonp({error:err.message});
  })
})

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
      res.status(err.status).jsonp({ error: err.message });
  });
});  


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
      res.status(err.status).jsonp({ error: err.message });
  });
});


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
      res.status(err.status).jsonp({ error: err.message });
  });
});

module.exports = router;
