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
    res.status(err.error.status).jsonp(err);
  });
});

/* Add Item to Cart */
router.post("/addItem", function(req, res, next) {
  cartService.addToCart(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

/* Remove Item from Cart */
router.post("/removeItem", function(req, res, next) {
  cartService.removeFromCart(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

/* Clear Cart */
router.post("/clearCart", function(req, res, next) {
  cartService.clearCart(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

/* Change Item Quantity */
router.post("/changeQuantity", function(req, res, next) {
  cartService.changeItemQuantity(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

/* Create Payment */
router.post("/createPayment", function(req, res, next) {
  cartService.createPayment(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

/* Change Payment Status */
router.post("/changePaymentStatus", function(req, res, next) {
  cartService.changePaymentStatus(req.body).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(err.error.status).jsonp(err);
  });
});

/* Possivel rota para alugar os items do cesto */
/*
receber no body seguinte estrutura:

 itensObj=   {
                "itens": [
                        {
                            codigo: "P1",
                            idloja: 1
                            quantidade: 2
                        },
                        {
                            codigo: "P2",
                            idloja: 1
                            quantidade: 1
                        }
                ]
              }
*/
/*
//adicionar a nova encomenda no servico de encomendas e notificacoes
            const codigo = "ENC"+username.toUpperCase()+Date.now(); //ex: ENCJOHNNY1730756161725

*/
router.post("/order", async function(req,res,next){ //depois inserir funcao middleware
  const itensObj = req.body.itensObj;
  const username = req.body.username;
  try{
    const result = await catalogoService.verifyAvailability(itensObj);
    if(result.status==200){
      cartService.clearCart(username).then(resp => { // clear cart 

          const payment = {
                            "username":username,
                            "localEntrega":req.body.localEntrega,
                            "inicioAluguer":req.body.inicioAluguer,
                            "fimAluguer":req.body.fimAluguer,
                            "modoPagamento":req.body.modoPagamento,
                            "status": "PENDING"
                          }

          cartService.createPayment(payment).then(resp => { // create payment
            
            
          }).catch(err => {
            res.status(400).jsonp(err);
          });  
        
      }).catch(err => {
        res.status(400).jsonp(err);
      });
    }
  }catch(err){
    res.status(err.response.data.status || 500).jsonp(err.response.message || "verifyAvailability error.");

  }
})


module.exports = router;
