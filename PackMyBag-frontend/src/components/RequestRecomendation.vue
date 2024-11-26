<template>
        <div class="stylist-request">
            <div class="text-wrapper">Request: {{ nome }} - Client: {{ cliente }}</div>
            <div class="div">Current Recommendation for {{ nome }}</div>
            <div class="request-details">
            <div class="overlap-group">
            <div class="text-wrapper-2"><b style="font-weight:bold">Styles</b>: {{ styles }}</div>
            <div class="text-wrapper-3"><b style="font-weight:bold">Colors</b>: {{ colors }}</div>
            <div class="text-wrapper-4"><b style="font-weight:bold">Number of Outfits</b>: {{  nroutfits  }}</div>
            <div class="text-wrapper-5"><b style="font-weight:bold">Budget</b>: {{ budget }}</div>
            <div class="text-wrapper-6"><b style="font-weight:bold">Don’t Include</b>: {{ dontinclude }}</div>
            <p class="p"><b style="font-weight:bold">Fabrics of Preference</b>: {{ preferences }}</p>
            <div class="text-wrapper-7"><b style="font-weight:bold">Occasion(s)</b>: {{ occasion }}</div>
            </div>
        </div>
        <div class="recommendation">
            <div class="overlap-group">
            <div class="overlap-group-2">
            <div class="text-wrapper-9">Added items:</div>
                <div class="text-wrapper-10">
                    <div v-for="(item,index) in items">
                        <div class="item">
                                <div class="hover_img">
                                    <a href="#" @click.prevent="goToItem(item.identificador)"><p v-if="index<limit">{{ item.designacao }}- {{  item.codigo }}</p><span><img :src="item.imagem" alt="image" height="250" /></span></a>
                                </div>
                                <button @click="removeItem(index)" class="remove-button" title="Remove item">✖</button>

                        </div> 
                    </div>                    
                </div>
                </div>
            <div class="frame">
            <div class="text-wrapper-11">Description</div>
            <input v-model="mydescription" class="frame-2">{{descricao}}</input>
            </div>
            <button @click="completeRequest" class="text-wrapper-12">Complete Recommendation</button>
            </div>
            </div>
        </div>    
</template>
<script>
import axios from 'axios';
import authHeader from '@/services/auth-header';
export default {
    props:{
        nome:String,
        descricao:String,
        cliente:String,
        styles:String,
        colors:String,
        nroutfits:String,
        budget:String,
        dontinclude:String,
        preferences:String,
        occasion:String,
        status:String,
        items: Array,
        idx:Number
    },
    data(){
        return {
            max:9,
            limit:1,
            mydescription:""
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
        },
        async removeItem(index){
            const result = await this.$swal.fire({
				title: "Are you sure you want to remove "+ this.items[index].designacao +" from the recommendation "+ this.nome+ " ?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Yes"
			});
			if(result.isConfirmed){
				let p;
				const header = authHeader();
				header['Content-Type'] = 'application/json';
                let data = {"nome": this.nome,"item": {"codigo":this.items[index].codigo,"designacao":this.items[index].designacao,"idLoja":this.items[index].idLoja}}
				try{
						let remove = await axios.delete(this.$apiGatewayUrl+'/api/recomendacoesService/removeItem',
                        { data, headers: header });
						if(remove.status===200) {
                            this.items.splice(index,1);
                            this.$swal.fire("The item was removed! ", "", "success");
                        }
				}catch(err){
					this.$swal.fire("Something went wrong! "+err, "", "error");
				}
			}
        },
        async completeRequest(){
            if(this.items.length==0){
                this.$swal.fire("You didn't insert any items to your recommendations! ", "", "error");
                return;
            }
            if(this.mydescription===''){
                this.$swal.fire("Insert a description to your recommendation! ", "", "error");
                return;
            }
            const result = await this.$swal.fire({
				title: "Are you sure you want send the recommendation "+ this.nome+ " ?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Yes"
			});
			if(result.isConfirmed){
				const header = authHeader();
				let config = {headers:header}
				header['Content-Type'] = 'application/json';
				try{
                        console.log("entrou");
                        console.log({
                            "nome":this.nome,
                            "descricao":this.mydescription,
                            "status":"completed"
                        });
						let edit = await axios.put(this.$apiGatewayUrl+'/api/recomendacoesService/pedidosEditDescricaoOrComplete',
						{
                            "nome":this.nome,
                            "descricao":this.mydescription,
                            "status":"completed"
                        }
						,
						config
						);
						console.log(edit);
                        this.$emit('recommendation_completed',this.idx);
					}catch(err){
						this.$swal.fire("Something went wrong! "+err, "", "error");
					}
			}
        },
        goToItem(id){
            this.$router.push({path:'/items/'+id})
        }
    }
}
</script>


<style lang="css" scoped>
@import '../assets/requestrecomendation.css';
</style>