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

module.exports = router;
