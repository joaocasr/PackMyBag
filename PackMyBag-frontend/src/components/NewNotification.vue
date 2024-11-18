<template>
    <div class="group">
        <div class="text-wrapper">{{ tipo }}</div>
        <p class="div">{{ descricao }}!</p>
        <img class="trash-icon" @click="deleteNotification" src="/NotificationsIMG/trash.png" />
        <div class="text-wrapper-2">{{ data }}</div>
    </div>
</template>

<script>
export default {
    props:{
        tipo:String,
        descricao:String,
        data:String,
        id:Number
    },
    methods:{
        deleteNotification(){
            this.$emit('removeNotification',this.id);
        }
        
    }
}
</script>


<style lang="css" scoped>
    @import '../assets/newnotification.css';
</style>