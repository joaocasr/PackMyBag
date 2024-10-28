<template>
    <div class="itemContainer">
        <div class="itemImg">
                <img class="imageIcon" alt="" :src="imgSrc">
          </div>
        <div class="container-descricao">
            <div class="container-descricao2">
                    <a :href="`/items/${this.idItem}`" class="cutwork-poplin-dress">{{descricao}}</a>
            </div>
        </div>
</div>

</template>
<script>
export default {
    props: {
        imgSrc: String,
        descricao: String,
        idItem: Number
    },
    data(){
        return{
            colorMap : {}
        }
    }
}

</script>
<style scoped src="../assets/itemrelacionado.css"></style>
