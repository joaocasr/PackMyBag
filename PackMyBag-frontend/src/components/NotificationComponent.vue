<template>
    <div class="notification-container">
        <div v-for="i in notifs" class="notificationClass">
            <img @click="closeNotification(i.id)" src="/FavouritesIMG/icon-cancel.svg" class="icon-cancel">
            <h2 style="margin-top:-40px">{{ i.tipo }}</h2> {{i.descricao}}
        </div>
    </div>
</template>


<script>
export default {
    props:{
        notifs:Array
    },
    methods:{
        closeNotification(id){

            this.$emit('closeNotification', id);
        }
    }
}
</script>


<style scoped lang="css">
@import '../assets/notification.css';
</style>