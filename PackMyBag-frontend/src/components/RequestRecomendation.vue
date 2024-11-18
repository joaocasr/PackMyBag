<template>
        <div class="stylist-request">
            <div class="text-wrapper">Request - Client: {{ cliente }}</div>
            <div class="div">Current Recommendation for {{ cliente }}</div>
            <div class="request-details">
            <div class="overlap-group">
            <div class="text-wrapper-2"><b style="font-weight:bold">Styles</b>: {{ styles }}</div>
            <div class="text-wrapper-3"><b style="font-weight:bold">Colors</b>: {{ colors }}</div>
            <div class="text-wrapper-4"><b style="font-weight:bold">Number of Outfits</b>: {{  nroutfits  }}</div>
            <div class="text-wrapper-5"><b style="font-weight:bold">Budget</b>: {{ budget }}</div>
            <div class="text-wrapper-6"><b style="font-weight:bold">Don’t Include</b>: {{ dontinclude }}</div>
            <p class="p"><b style="font-weight:bold">Fabrics of Preference</b>: {{ preferences }}</p>
            <div class="text-wrapper-7"><b style="font-weight:bold">Occasion(s)</b>: {{ occasion }}</div>
            </div>
        </div>
        <div class="recommendation">
            <div class="overlap-group">
            <div class="overlap-group-2">
            <div class="text-wrapper-9">Added items:</div>
                <div class="text-wrapper-10">
                    <p v-for="(item,index) in items">
                        <p v-if="index<limit">{{ item.nome }}</p>
                    </p>                    
                </div>
                <button class="expand" @click="expand">Expand</button>
            </div>
            <div class="frame">
            <div class="text-wrapper-11">Description</div>
            <input class="frame-2"></input>
            </div>
            <button class="text-wrapper-12">Complete Recommendation</button>
            </div>
            </div>
        </div>    
</template>
<script>
export default {
    props:{
        cliente:String,
        styles:String,
        colors:String,
        nroutfits:String,
        budget:String,
        dontinclude:String,
        preferences:String,
        occasion:String,
        status:String,
        items: Array,
        idx:Number
    },
    data(){
        return {
            max:9,
            limit:1
        }
    },
    created(){
        console.log(this.items);
        if(this.items.length<this.max) this.limit = this.items.length;
        else this.limit = this.max;
    },
    methods:{
        expand(){
            this.$emit('expand',this.idx);
        }
    }
}
</script>


<style lang="css" scoped>
@import '../assets/requestrecomendation.css';
</style>