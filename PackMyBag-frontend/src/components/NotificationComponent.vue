<template>
    <div class="notification-container">
        <div v-for="i in notificacoes" class="notificationClass">
            <img @click="closeNotification(i.id)" src="/FavouritesIMG/icon-cancel.svg" class="icon-cancel">
            {{i.message}}
        </div>
    </div>
</template>


<script>
export default {
    data(){
        return {
            notificacoes:[{"id":1,"message":"notification1"},{"id":2,"message":"notification 2"},{"id":3,"message":"notification3"}]
        }
    },
    methods:{
        closeNotification(id){
            this.notificacoes = this.notificacoes.filter( (n)=> n.id !== id);
        }
    }
}
</script>


<style scoped lang="css">
@import '../assets/notification.css';
</style>