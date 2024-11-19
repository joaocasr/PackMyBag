<template>

    <div class="text-wrapper-20">Items:</div>

    <p v-for="(item,index) in items">
            <p v-if="index<limit">{{ item.nome }}</p>
    </p> 
    
    <div class="text-wrapper-22">Description:</div>
    <p class="layer-a-crisp-white">
        {{descricao}}
    </p>

</template>

<script>
export default {
    props:{
        descricao:String,
        items: Array,
        idx:Number
    },
    data(){
        return {
            max:9,
            limit:1
        }
    },
    created(){
        console.log(this.items);
        if(this.items.length<this.max) this.limit = this.items.length;
        else this.limit = this.max;
    },
    methods:{
        expand(){
            this.$emit('expand',this.idx);
        }
    }
}
</script>


<style lang="css" scoped>
@import '../assets/userRecomendationIndividual.css';
</style>