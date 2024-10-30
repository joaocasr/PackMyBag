<template>
  	<div class="favouritecomponent">
    		<div class="divclass">€ {{ preco }}</div>
    		<img class="image-18-icon" alt="" :src="imagem">
    		
    		<div class="cutwork-poplin-dress"><a :href="`/items/${idItem}`">{{nome}}</a></div>
    		<img class="icon-cancel" alt="" src="/FavouritesIMG/icon-cancel.svg">
    		
    		<div class="in-stock">{{disponibilidade}}</div>
  	</div>
</template>

<script>
export default {
    props:{
        preco:Number,
        imagem:String,
        nome:String,
        disponibilidade:String,
        idItem:Number
    }
}
</script>

<style lang="css" scoped>
@import '../assets/favouritecomponent.css';
</style>
