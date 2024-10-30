var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const cors = require('cors')
var catalogoRouter = require('./routes/catalogoRouter');
var cartRouter = require('./routes/cartRouter');
var favoritosRouter = require('./routes/favoritosRouter');
var notificacoesRouter = require('./routes/notificacoesRouter');
const { APIcatalogo } = require('./public/javascripts/entrypoints')
const { APIfavoritos } = require('./public/javascripts/entrypoints')
const { APINotificacoes } = require('./public/javascripts/entrypoints')
const { APIcart } = require('./public/javascripts/entrypoints')

var app = express();

app.use(cors({origin:'*'}))
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use(APIcatalogo, catalogoRouter);
app.use(APIfavoritos, favoritosRouter);
app.use(APIcart, cartRouter);
app.use(APINotificacoes, notificacoesRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  res.status(err.status || 500);
});

module.exports = app;
