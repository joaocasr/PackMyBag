import axios from 'axios';
import { jwtDecode } from "jwt-decode";
import { auth } from '@/store/auth-module';

const API_URL = "http://localhost:8888/api/utilizadoresService/"
const headers = {
    'Content-Type': 'application/json',
}

class AuthService{
    async login(user){
        let response = await axios.post(API_URL+"signin",
            {
                username: user.username,
                password: user.password
            },
            { headers }
        );
        if(response.data.accessToken){
            localStorage.setItem('user',JSON.stringify(response.data))
        }
        return response.data;
    };
    logout(){
        auth.state.status.loggedIn=false;
        localStorage.removeItem('user');
    };
    async signUpUser(user){
        let response = await axios.post(API_URL+"signup/user",
            {
                username: user.username,
                password: user.password,
                nome: user.nome,
                email: user.email,
                genero: user.genero,
                morada: user.address,
                nrTelemovel: user.nrTelemovel
            },
            {headers}
        );
        return response;
    };
    async signUpEstilista(estilista){
        let response = await axios.post(API_URL+"signup/estilista",
            {
                username: estilista.username,
                password: estilista.password,
                nome: estilista.nome,
                email: estilista.email,
                genero: estilista.genero,
                bio: estilista.bio
            },
            {headers}
        );
        return response;
    };
    async signUpTecnico(tecnico){
        let response = await axios.post(API_URL+"signup/tecnico",
            {
                username: tecnico.username,
                nome: tecnico.nome,
                password: tecnico.password,
                nomeLoja: tecnico.nomeLoja,
                email: tecnico.email
            },
            {headers}
        );
        return response;
    };
    getToken(){
        let user = localStorage.getItem('user');
        console.log(user);
        if(user==null) return null;
        let decoded= jwtDecode(JSON.parse(user).accessToken);
        if(decoded.exp < (Date.now()/1000)){
            auth.state.status.loggedIn=false;
            return null;
        }
        else{
            auth.state.status.loggedIn=true;
            return decoded
        };
    }
}

export default new AuthService();
