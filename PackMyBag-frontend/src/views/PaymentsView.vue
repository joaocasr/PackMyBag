<template>
    <NavBarComponent></NavBarComponent>
    <FooterComponent></FooterComponent>
</template>
<script>
import FooterComponent from '@/components/FooterComponent.vue';
import NavBarComponent from '@/components/NavBarComponent.vue';

export default {
    components:{
        FooterComponent,
        NavBarComponent
    }
}
</script>


<style lang="css" scoped>
@import '../assets/payments.css';
</style>