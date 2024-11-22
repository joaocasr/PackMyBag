<template>

    <div class="text-wrapper-20">Items:</div>

    <ul v-for="item in items">
        <div class="hover_img">
            <li class="txt-items">{{ item.designacao }} <a href="#" @click.prevent="gotoItem(item.identificador)">({{ item.codigo }})<span><img :src="item.imagem" alt="image" height="250" /></span></a></li>
        </div>
    </ul> 
    
    <div class="text-wrapper-22">Description:</div>
    <p class="layer-a-crisp-white">
        {{descricao}}
    </p>
    <button class="addBtn" @click="addItemsToCart">
        Add Items To Cart
    </button>

</template>

<script>
import authService from '@/services/auth-service';
import axios from 'axios';

export default {
    props:{
        descricao:String,
        items: Array,
        username:String,
        nome:String,
        email:String
    },
    methods:{
        gotoItem(id){
            this.$router.push({path:'/items/'+id})
        },
        async addItemsToCart(){
			const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
            for(let p=0; p<this.items.length;p+=1){
                try{
                    let r = await axios.post('http://localhost:8888/api/cartService/addItem',
                    {
                        "codigo":this.items[p].codigo,
                        "idLoja":this.items[p].idLoja,
                        "username":this.username,
                        "nome":this.nome,
                        "email":this.email,
                        "designacao":this.designacao,
                        "imagem":this.items[p].imagem,
                        "preco":this.items[p].preco,
                        "quantidade":1
                    },
                    config
                    );
                    return r;
                }catch(err){
                    return err;
                }
            }
		}
    }
}
</script>


<style lang="css" scoped>
@import '../assets/userRecomendationIndividual.css';
</style>