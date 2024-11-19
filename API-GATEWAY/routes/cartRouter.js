var express = require('express');
var router = express.Router();
const cartService = require('../microservices/cartService')
const catalogoService = require('../microservices/catalogoService')
const validate = require('../middleware/index')

/* Get Cart Items */
router.get("/:username", function(req, res, next) {
  cartService.getCartItems(req.params.username).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(400).jsonp(err);
  });
});

/* Add Item to Cart */
router.post("/addItem", validate.verifyToken, function(req, res, next) {
  cartService.addToCart(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(403).jsonp(err);
  });
});

/* Remove Item from Cart */
router.post("/removeItem", function(req, res, next) {
  cartService.removeFromCart(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(400).jsonp(err);
  });
});

/* Clear Cart */
router.post("/clearCart", function(req, res, next) {
  cartService.clearCart(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(400).jsonp(err);
  });
});

/* Get User transactions */
router.get("/transactions/:username", function(req, res, next) {
  cartService.getTransactions(req.params.username).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});

/* Change Payment Status */
router.post("/changePaymentStatus", function(req, res, next) {
  cartService.changePaymentStatus(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(400).jsonp(err);
  });
});




/* just payment */
router.post("/newpayment", /*validate.verifyToken, */ async function(req,res,next){
  try{

    const username = req.body.username;
    const codigo = "FOR"+username.toUpperCase()+Date.now();
    const payment = {
                      "username":username,
                      "codigo": codigo,
                      "total":req.body.total,
                      "modoPagamento":req.body.modoPagamento,
                      "status": "PENDING",
                      "dataGeracao":req.body.dataGeracao
                    }
    
      let r = await cartService.createPaymentForms(payment); // create payment
      res.status(200).jsonp(r);
  }catch(err){
    res.status(400).jsonp(err);

  }
})


/* rota para alugar os items do cesto */
router.post("/order", validate.verifyToken, async function(req,res,next){
  const itensObj = req.body.itensObj;
  const username = req.body.username;
  try{
    console.log(itensObj);
    const result = await catalogoService.verifyAvailability(itensObj);
    if(result.status==200){
          console.log("passou")
          console.log(username)
    
          const codigo = "ENC"+username.toUpperCase()+Date.now();
          const payment = {
                            "username":username,
                            "codigo": codigo,
                            "localEntrega":req.body.localEntrega,
                            "inicioAluguer":req.body.inicioAluguer,
                            "fimAluguer":req.body.fimAluguer,
                            "total":req.body.total,
                            "modoPagamento":req.body.modoPagamento,
                            "dataGeracao":req.body.dataGeracao,
                            "status": "PENDING",
                            "items":itensObj.itens
                          }
          console.log(payment);
          try{
            let r = await cartService.createPayment(payment); // create payment
            res.status(200).jsonp(r);
          }catch(err){
            res.status(400).jsonp(err);
          }            
    }
  }catch(err){
    res.status(400).jsonp(err);

  }
})


module.exports = router;
