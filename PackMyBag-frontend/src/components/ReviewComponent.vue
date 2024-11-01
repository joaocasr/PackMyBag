<template>
<div class="review1">
      			<div class="gabriel-r">{{ username }}</div>
      			<div class="i-love-the">{{ descricao }} | {{  timestamp }}</div>
      			<img class="generic-user-icon-13-262266219" alt="" src="/DetailedItemIMG/generic-user-icon-13-2622662197-removebg-preview 1.png">
      			
      			<div class="star">
      			</div>
      			<div class="star-parent">
					<Rating id="estrelas" :modelValue="classificacao" @update:modelValue="classificacao = $event" readonly :cancel="false" />
        				
					</div>
				<div v-if="currentUser==username">
					<div class="reviewer-options" @click="settingsOpened">...</div>
					<div v-if="optionsOpened==true" class="dropdown-settings-content">
						<div id="row-option" @click="showConfirmation()" >Delete</div>
					</div>	
				</div>
				
    		</div>
</template>
<script>
import axios from 'axios';
import Rating from 'primevue/rating';
export default {
	props:{
		classificacao:0,
		descricao:String,
		currentUser:String,
		username:String	,
		profileImg:String,
		timestamp:String,
		idItem:Number
	},
	data(){
		return{
			optionsOpened:false
		}
	},
	components: {
		Rating
	},
	methods:{
		settingsOpened(){
			this.optionsOpened=!this.optionsOpened;
		},
		showConfirmation(){
			this.$swal.fire({
				title: "Are you sure you want to delete your review?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Delete",
				}).then((result) => {
				if (result.isConfirmed) {
					try{
						this.deleteReview();
						this.$swal.fire("Your review was deleted!", "", "success");
					}catch(err){
						this.$swal.fire("Something went wrong!", "", "error");
					}
				}
			});
			this.optionsOpened=false;
		},
		deleteReview(){
			axios.delete('http://localhost:8888/api/catalogoService/items/'+this.idItem+'/delreview/'+this.username).then(resp=>{
				console.log(resp);
			})
		}
	}
}
</script>

<style lang="css" scoped>
@import '../assets/review.css';
</style>