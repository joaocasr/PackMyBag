<template>
    <div class="produto">
    <img class="image-18-icon" :src="imagemUrl" alt="Imagem do Artigo">
      <div class="cutwork-poplin-dress">{{ nomeArtigo }}</div>
      <div class="preo-45-parent">
        <div class="preo-45">Preço: €{{ preco }}</div>
        <div class="tamanho-m">Quantidade: {{ quantidade }}</div>
        <div class="tamanho-m">Tamanho: {{ tamanho }}</div>
        <div class="tamanho-m">Cor: {{ cor }}</div>
        <div class="cdigo-do-artigo">Código do artigo: {{ codigoArtigo }}</div>
      </div>
      <!--<div class="escrever-um-comentrio" @click="goToCommentPage">ESCREVER UM COMENTÁRIO</div>-->
    </div>
  </template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import OrderComponentView from '@/components/OrderComponentView.vue';

export default {
    components: {
        NavBarComponent,
        FooterComponent,
        OrderComponentView
    },
    props: {
        imagemUrl: {
        type: String,
        required: true
        },
        nomeArtigo: {
        type: String,
        required: true
        },
        preco: {
        type: Number,
        required: true
        },
        quantidade: {
        type: Number,
        required: true
        },
        tamanho: {
        type: String,
        required: true
        },
        cor: {
        type: String,
        required: true
        },
        codigoArtigo: {
        type: String,
        required: true
        }
    },
    methods: {
        goToCommentPage() {
          this.$router.push({ name: 'CommentPage', params: { codigoArtigo: this.codigoArtigo } });
        }
    }
}

</script>

<style lang="css" scoped>
@import '../assets/OrderItemView.css';
</style>