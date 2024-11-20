var express = require('express');
var router = express.Router();
const validate = require('../middleware/index')
const recomendacoesService = require('../microservices/recomendacoesService')


/* Get User pedidos */
router.get("/getpedidosC/:username", function(req, res, next) {
  recomendacoesService.getPedidosCliente(req.params.username).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});


/* Get Stylist pedidos */
router.get("/getpedidosS/:username", function(req, res, next) {
  recomendacoesService.getPedidosEstilista(req.params.username).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});


/* create new pedido */
router.post("/newPedido", validate.verifyToken,  async function(req,res,next){
  try{

    const pedido = {
                      "usernameEstilista":req.body.nomeEstilista,
                      "usernameCliente": req.body.idCliente,
                      "estilos":req.body.estilos,
                      "cores":req.body.cores,
                      "nrOutfits": req.body.nrOutfits,
                      "orcamento":req.body.orcamento,
                      "peçasExcluidas":req.body.pecasExcluidas,
                      "fabricsPreferences":req.body.fabricsPrefered,
                      "occasions":req.body.ocasioes
                    }
    
      let r = await recomendacoesService.createNewPedido(pedido); // create payment
      //falta adicionar o pedido no estilista
      res.status(200).jsonp(r);
  }catch(err){
    res.status(400).jsonp(err);

  }
})



module.exports = router;
