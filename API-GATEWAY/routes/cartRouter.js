var express = require('express');
var router = express.Router();
const cartService = require('../microservices/cartService')

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

module.exports = router;
