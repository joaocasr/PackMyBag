var express = require('express')
var router = express.Router();
var utilizadoresService = require('../microservices/utilizadoresService')
const validate = require('../middleware/index')
const multer = require('multer');
const FormData = require('form-data');
const fs = require('fs');
const path = require('path');


const uploadDir = path.join(__dirname, 'uploads');
if (!fs.existsSync(uploadDir)) {
    fs.mkdirSync(uploadDir);
}

const upload = multer({ dest: 'uploads/' });

router.post('/signin', async function(req,res,next){
    const username = req.body.username;
    const password = req.body.password;
    try {
        const resp = await utilizadoresService.signIn(username, password);
        //console.log(resp.response);
        if(resp.response) res.status(resp.response.status).jsonp(resp);
        else res.jsonp(resp);
    } catch (err) {
        //console.log(err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.post('/signup/user', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const genero = req.body.genero;
    const morada = req.body.morada;
    const nrTelemovel= req.body.nrTelemovel;

    try {
        const resp = await utilizadoresService.signUpUser(username, nome, password, email,genero,morada,nrTelemovel);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/signup/estilista', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const genero= req.body.genero;
    const bio = req.body.bio;

    try {
        const resp = await utilizadoresService.signUpEstilista(username, nome, password, email,genero,bio);
        console.log(resp);
        res.jsonp(resp);
    } catch (err) {
        console.log(err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/signup/tecnico', async function(req,res,next){
    const username = req.body.username;
    const nome = req.body.nome;
    const password = req.body.password;
    const email = req.body.email;
    const nomeLoja = req.body.nomeLoja;
    try {
        const resp = await utilizadoresService.signUpTecnico(username, nome, password, email,nomeLoja);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.get('/estilistas',validate.verifyToken, async function(req,res,next){
    try {
        const page = req.query.page;
        const number = req.query.number;    
        const resp = await utilizadoresService.getEstilistas(req.token,page,number);
        res.jsonp(resp);
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.get('/profileImg/:username', async function(req,res,next){
    try {
        const username = req.params.username;
        const resp = await utilizadoresService.getProfileImg(username);
        res.set('Content-Type', resp.headers['content-type']);
        res.send(resp.data); 
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.get('/profileInfo/:username', async function(req,res,next){
    try {
        const username = req.params.username;
        const resp = await utilizadoresService.getProfileInfo(username);
        res.send(resp); 
    } catch (err) {
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});


router.post('/updateImage', upload.single('profile_image'), async function(req, res, next) {
    try {
        if (!req.file || !req.body.username) {
            throw new Error("Missing file or username");
        }

        // Log the file path
        console.log('File path:', req.file.path);

        const form = new FormData();
        form.append('profile_image', fs.createReadStream(req.file.path), {
            filename: req.file.originalname,
            contentType: req.file.mimetype
        });
        form.append('username', req.body.username);
        console.log(form);

        const resp = await utilizadoresService.saveImage(form);
        console.log(resp);
        res.send(resp);
    } catch (err) {
        console.error('Error:', err);
        res.status(err.status || 500).jsonp(err.error || "Internal Server Error");
    }
});

router.post('/updateProfile/normal',validate.verifyToken, async (req, res) => {
    try {
        console.log('Forwarding payload for normal user:', req.body);
        const result = await utilizadoresService.updateNormalProfile(req.token,req.body);
        res.json(result);
    } catch (error) {
        console.error('Error forwarding to updateProfile for Normal user:', error);
        res.status(error.status || 500).json({ error: error.message });
    }
  });
  

// Route for updating Estilista profile
router.post('/updateProfile/estilista',validate.verifyToken, async (req, res) => {
    console.log('Forwarding payload for estilista user:', req.body);

    try {
        const result = await utilizadoresService.updateEstilistaProfile(req.token,req.body);
        res.json(result);
    } catch (error) {
        console.error('Error forwarding to updateProfile for estilista user:', error);
        res.status(error.status || 500).json({ error: error.message });
    }
});

// Route for updating Tecnico profile
router.post('/updateProfile/tecnico',validate.verifyToken, async (req, res) => {
    console.log('Forwarding payload for tecnico user:', req.body);

    try {
        const result = await utilizadoresService.updateTecnicoProfile(req.token,req.body);
        res.json(result);
    } catch (error) {
        console.error('Error forwarding to updateProfile for tecnico user:', error);
        res.status(error.status || 500).json({ error: error.message });
    }
});
  
  

module.exports = router;
