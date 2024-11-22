var express = require('express');
var router = express.Router();
const cartService = require('../microservices/cartService')
const catalogoService = require('../microservices/catalogoService')
const validate = require('../middleware/index')
const encomendaService = require('../microservices/encomendaService')
const recomendacoesService = require('../microservices/recomendacoesService')

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
  const page = req.query.page;
  const number = req.query.number;
  cartService.getTransactions(req.params.username,page,number).then(resp => {
    res.jsonp(resp);
  }).catch(err => {
    res.status(404).jsonp(err);
  });
});

/* just payment */
router.post("/newpayment", validate.verifyToken,  async function(req,res,next){
  try{

    const username = req.body.username;
    const payment = {
                      "username":username,
                      "codigo": req.body.codigo,
                      "total":req.body.total,
                      "modoPagamento":req.body.modoPagamento,
                      "status": "PENDING",
                      "dataGeracao":req.body.dataGeracao
                    }
    
      let r = await cartService.createPaymentForms(payment); // create payment
      //falta adicionar o pedido no estilista
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
    
          const codigo = "CART"+username.toUpperCase()+Date.now();
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


/* actually pay */
router.post("/pay", validate.verifyToken, async function(req,res,next){
  console.log("let's pay");
  try{

      if(req.body.ptype==="FORM"){
        //alterar status desse pedido para payed com o codigo no microservico de recomendacoes | POST
        try{
          const statusPayment = {
                "codigo":req.body.codigo,
                "username":req.body.username,
                "status":"PAYED"
          }  
          let resp1 = await cartService.changePaymentStatus(statusPayment);
          console.log("resp 1")
          console.log(resp1);
          if(resp1.status===200){ 

            try{
                let resp2 = await recomendacoesService.changeStatus(req.body.codigo,"PAYED")
                res.jsonp(resp2);
            }catch(err){
              console.log(err);
            }
          }
        }catch(err){
            console.log(err);
        }
      }
      if(req.body.ptype==="CART"){
        try{
          const statusPayment = {
                "codigo":req.body.codigo,
                "username":req.body.username,
                "status":"PAYED"
          }  
          let resp1 = await cartService.changePaymentStatus(statusPayment);
          console.log("resp 1")
          console.log(resp1);
          if(resp1.status===200){ //get dos items associados ao pagamento

            try{  
              
                    let resp2 = await cartService.getpayment(req.body.codigo); 
                    console.log("resp 2");
                    console.log(resp2);         
                    let paymentInfoItems = resp2;

                    console.log("a imprimir a nova encomenda")
                    const encomenda = {
                      "codigoEncomenda":req.body.codigo,
                      "dataEntrega": paymentInfoItems.inicioAluguer,
                      "dataDevolucao": paymentInfoItems.fimAluguer,
                      "localEntrega": paymentInfoItems.localEntrega,
                      "status": "Pago",
                      "clienteUsername": req.body.username,
                      "clienteNome": req.body.nome,
                      "clienteEmail": req.body.email,
                      "itens": paymentInfoItems.items,
                      "preco": req.body.total,
                      "taxaEntrega": 6.0
                    }
                    console.log(encomenda);
                    try{
                      let resp3 = await encomendaService.createEncomenda(encomenda);
                      res.status(200).jsonp(resp3);
                    }catch(err){
                      console.log(err);
                    }
            }catch(err){
              console.log(err);
            }
          }
        }catch(err){
          console.log(err);
        }
      }
  }catch(err){
    console.log(err);
    res.status(400).jsonp(err);

  }
})

// // Rota para criar pagamento PayPal
// router.post("/paypal/create",  async function(req, res, next) { //validate.verifyToken,

//   try {
//       const paymentData = {
//           method: req.body.method,
//           amount: req.body.amount,
//           currency: req.body.currency,
//           description: req.body.description
//       };
//       let response = await cartService.createPaypalPayment(paymentData);
//       res.status(200).jsonp(response);
//   } catch (err) {
//       res.status(400).jsonp(err);
//   }
// });

/* Create PayPal Payment */
router.post("/paypal/create", async function(req, res, next) {
  const method = req.body.method;
  const amount = req.body.amount;
  const currency = req.body.currency;
  const description = req.body.description;

  try {
    let response = await cartService.createPaypalPayment(method, amount, currency, description);
    console.log(response);
    res.status(200).send(response);
  } catch (err) {
    res.status(400).jsonp(err);
  }
});


// Rota para capturar pagamento PayPal
router.get("/paypal/success", async function(req, res, next) {
  try {
      const paymentId = req.query.paymentId;
      const payerId = req.query.PayerID;
      let response = await cartService.capturePaypalPayment(paymentId, payerId);
      res.status(200).jsonp(response);
  } catch (err) {
      res.status(400).jsonp(err);
  }
});

// Rota para cancelar pagamento PayPal
router.get("/paypal/cancel", async function(req, res, next) {
  try {
      let response = await cartService.cancelPaypalPayment();
      res.status(200).jsonp(response);
  } catch (err) {
      res.status(400).jsonp(err);
  }
});

// Rota para tratar erro de pagamento PayPal
router.get("/paypal/error", async function(req, res, next) {
  try {
      let response = await cartService.errorPaypalPayment();
      res.status(200).jsonp(response);
  } catch (err) {
      res.status(400).jsonp(err);
  }
});

module.exports = router;
