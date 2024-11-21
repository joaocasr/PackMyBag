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
export default {
    props:{
        descricao:String,
        items: Array,
    },
    methods:{
        gotoItem(id){
            this.$router.push({path:'/items/'+id})
        }
    }
}
</script>


<style lang="css" scoped>
@import '../assets/userRecomendationIndividual.css';
</style>