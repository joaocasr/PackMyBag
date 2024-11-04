<template>
           
  	<div class="stylistcomponent">
    		<div class="stylistcomponent-child">
    		</div>
    		<div class="image-8-wrapper">
				<img v-if="profileImage.length==0" class="image-8-icon" alt="" src="/StylistsIMG/no-user.png">
				<img v-if="profileImage.length!=0" class="image-8-icon" alt="" :src="profileImage">
      			
    		</div>
    		<div class="stylistcomponent-item">
    		</div>
    		<div class="gonalo-vale">{{nome}}</div>
    		<div class="stylistcomponent-inner">
      			<div class="frame-parent">
        			<Rating id="avrgestrelas" :modelValue="rating" @update:modelValue="rating = $event" readonly/>	
      			</div>
    		</div>
    		<div class="an-expert-in-container">
      			<p class="an-expert-in">{{bio}}</p>
    		</div>
			<button class="vector-icon" @click="goToForms()"></button>
    		
  	</div>


</template>

<script>
import Rating from 'primevue/rating';
export default {
	props:{
		nome:String,
		profileImage:String,
		bio:String,
		rating:String
	},
	data(){
		return
		{
			defaultImg:"https://static.thenounproject.com/png/354384-200.png"
		}
	},
	components:{
		Rating
	},
	methods:{
		goToForms(){
			this.$router.push({ path: '/stylists/forms' });
		}
	}
}
</script>


<style scoped src="../assets/stylistIndividual.css"></style>
