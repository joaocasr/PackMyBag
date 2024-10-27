<template>
<div class="review1">
      			<div class="gabriel-r">{{ nome }}</div>
      			<div class="i-love-the">{{ descricao }} | {{  timestamp }}</div>
      			<img class="generic-user-icon-13-262266219" alt="" src="/DetailedItemIMG/generic-user-icon-13-2622662197-removebg-preview 1.png">
      			
      			<div class="star">
      			</div>
      			<div class="star-parent">
					<Rating id="estrelas" :modelValue="classificacao" @update:modelValue="classificacao = $event" readonly :cancel="false" />
        				
      			</div>
				
    		</div>
</template>
<script>
import Rating from 'primevue/rating';
export default {
	props:{
		classificacao:0,
		descricao:String,
		nome:String	,
		profileImg:String,
		timestamp:String
	},
	components: {
		Rating
	}
}
</script>

<style lang="css" scoped>
@import '../assets/review.css';
</style>