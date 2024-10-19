var express = require('express');
var router = express.Router();
const { APIcatalogo } = require('../public/javascripts/entrypoint')
const catalogoService = require('../microservices/catalogoService')

/*Get Items By Page*/
router.get(APIcatalogo, function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getItemsByPage(page, number).then(items => {
      res.jsonp(items);
  }).catch(err => {
      res.status(err.status).jsonp({ error: err.error });
  });
});

/*Get All Items*/
router.get(APIcatalogo+"/all", function(req, res, next) {
  catalogoService.getAllItems().then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.status).jsonp({ error: err.error });
  });
});

/*Show Details from an Item Page*/
router.get(APIcatalogo+"/items/:id", function(req, res, next) {
  catalogoService.showItemDetails(req.params.id).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.status).jsonp({ error: err.message });
  });
});

/*Get Reviews from a Item id with pagination*/
router.get(APIcatalogo+"/items/:id/reviews", function(req, res, next) {
  const page = req.query.page;
  const number = req.query.number;
  catalogoService.getReviews(req.params.id,page,number).then(items => {
      res.jsonp(items);
  }).catch(err => {
    res.status(err.status).jsonp({ error: err.message });
  });
});  

/*Adicionar Review*/
router.post(APIcatalogo+"/items/:id/review", function(req, res, next) {
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

module.exports = router;
