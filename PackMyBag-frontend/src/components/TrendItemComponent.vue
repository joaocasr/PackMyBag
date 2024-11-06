<template>
    <div class="itemContainer">
        <div class="itemImg">
                <img class="imageIcon" alt="" :src="imgSrc">
          </div>
        <div class="container-descricao">
            <div class="container-descricao2">
                    <p class="cutwork-poplin-dress" style="font-weight: bold;">Acquisitions: {{ aquisicoes }}</p>
                    <p class="rs995000">{{ descricao }}</p>
            </div>
        </div>
</div>

</template>
<script>
export default {
    props: {
        imgSrc: String,
        descricao: String,
        aquisicoes: Number,
        iditem:Number
    },
    data(){
        return{
            colorMap : {}
        }
    }
}
</script>
<style scoped src="../assets/itemcatalogue.css"></style>