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
        <img class="image-18-icon" alt="" :src="image">
        <div class="cutwork-poplin-dress">{{ nome }}</div>
        <div @click="handleItemRemove()">
            <img class="icon-cancel" alt="" src="/FavouritesIMG/icon-cancel.svg">
        </div>
    </div>
    
</template>
<script>
import VueSelect from "vue3-select-component";
import axios from 'axios';
import authHeader from "@/services/auth-header";
export default {
    props:{
        preco:Number,
        nome:String,
        codigo:String,
        image:String,
        quantidade:Number,
        idloja:Number,
        username:String,
        index:Number
    },
    components:{
        VueSelect
    },
    data(){
        return {
            selectedOption:'',
			typeOptions: [],
            subtotal: 0
        }
    },
    created(){
        let nr = 3;
        this.selectedOption = ''+this.quantidade;
        for(let l = 1 ; l<=nr; l+=1){
            let v = ''+l
            this.typeOptions.push({label: v, value: v});
        }
        this.subtotal = this.preco;
    },
    watch:{
        selectedOption:function(newvalue,oldvalue){
            this.subtotal = this.preco * newvalue;
            this.$emit('newQuantity',newvalue)
        }
    },
    methods:{
        async removeItemCart(){
            const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
            try{
                let r = await axios.post(this.$apiGatewayUrl+'/api/cartService/removeItem',
                    {
                        "codigo":this.codigo,
                        "idloja":this.idloja,
                        "username":this.username
                    },
                    config
                );
                return r;
            }catch(err){
                return err;
            }
        },
        async handleItemRemove(){
            const result = await this.$swal.fire({
				title: "Do you want to remove the item '" + this.nome.toLowerCase() + "' from your cart?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Remove"
			});
			if (result.isConfirmed) {
				let r = await this.removeItemCart();
				if (r && r.status == 200) {
					this.$swal.fire("Removed! The item was removed from your cart.", "", "success");
                    this.$emit('itemRemoved',this.index)
				} else {
					let msg="";
					if(r.response) msg = r.response.data.message;
					this.$swal.fire("Something went wrong! "+msg, "", "error");
				}
			}
        }
    }
}
</script>
<style lang="css" scoped>
@import '../assets/cartitem.css';
</style>