<template>
	<div class="cart-section">
        <div class="div">{{ preco }}€</div>
        <div class="div1">{{ subtotal }}€</div>
        <div class="quantity">
        <div class="parent">
            <VueSelect v-model="selectedOption" :options="typeOptions"/>
        <div class="drop-up-small-parent">

        </div>
        </div>
        </div>
        <img class="image-18-icon" alt="" src="/FavouritesIMG/image 18.png">
        <div class="cutwork-poplin-dress">{{ nome }}</div>
        <img class="icon-cancel" alt="" src="/FavouritesIMG/icon-cancel.svg">
    </div>
    
</template>
<script>
import VueSelect from "vue3-select-component";
export default {
    props:{
        preco:Number,
        nome:String,
        codigo:String,
        image:String
    },
    components:{
        VueSelect
    },
    data(){
        return {
            selectedOption:'1',
			typeOptions: [{ label: '1', value: '1' },{ label: '2', value: '2' },{ label: '3', value: '3' }],
            subtotal: 0
        }
    },
    created(){
        this.subtotal = this.preco;
    },
    watch:{
        selectedOption:function(newvalue,oldvalue){
            this.subtotal = this.preco * newvalue;
            this.$emit('newQuantity',newvalue)
        }
    }
}
</script>
<style lang="css" scoped>
@import '../assets/cartitem.css';
</style>