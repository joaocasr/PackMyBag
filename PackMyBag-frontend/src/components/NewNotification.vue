<template>
    <div class="group">
        <div class="text-wrapper">{{ tipo }}</div>
        <p class="div">{{ descricao }}!</p>
        <img class="trash-icon" @click="deleteNotification" src="img/trash-icon-9-1540048380-removebg-preview-1.png" />
        <div class="text-wrapper-2">{{ data }}</div>
    </div>
</template>

<script>
export default {
    props:{
        tipo:String,
        descricao:String,
        data:String,
        id:Integer
    },
    methods:{
        deleteNotification(){
            this.$emit('removeNotification',this.id);
        }
        
    }
}
</script>


<style lang="css" scoped>
    @import '../assets/newcomponent.css';
</style>