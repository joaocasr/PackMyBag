<template>
    <main>
        <NavBarComponent></NavBarComponent>
        
  	
  	
            <div class="formpage">
                    <img class="formpage-child" alt="" src="/FormsIMG/Line 17.png">
                    
                    <div class="send-mail-parent">
                        <div class="send-mail">
                                <div class="icon-send">
                                </div>
                        </div>
                        <div class="enter-your-email"> {{this.nomeEstilista}} </div>
                        <div class="avatar">
                                <img class="shape-icon" alt="" src="/FormsIMG/Shape.png">
                                
                        </div>
                    </div>
                    <div class="form4">
                        <div class="rectangleform">
                        </div>
                                <!-- <div class="next">NEXT</div> -->
                        <button class="nextbtn" @click="showConfirmPopup">NEXT</button>
                        
                        <img class="image-36-icon" alt="" src="/FormsIMG/image 36.png">
                        
                        <img class="image-37-icon" alt="" src="/FormsIMG/image 37.png">
                        
                        <img class="image-38-icon" alt="" src="/FormsIMG/image 38.png">
                        
                        <div class="form4-child">
                            <input type="radio" name="paymethod" value="multibanco" style="transform: scale(2.5);" @click="changePaymentType('multibanco')"/>
                        </div>
                        <div class="form4-item">
                            <input type="radio" name="paymethod" value="mbway" style="transform: scale(2.5);" @click="changePaymentType('mbway')"/>
                        </div>
                        <div class="form4-inner">
                            <input type="radio" name="paymethod" value="paypal" style="transform: scale(2.5);" @click="changePaymentType('paypal')"/>
                        </div>
                        <div class="q1">To submit this form and receive a response from the stylist, you must pay 7€</div>
                        <div class="item-history-parent">
                                <div class="item-history">
                                    <img class="bg-icon" alt="" src="/FormsIMG/bg.svg">
                                    
                                    <div class="group">
                                            <div class="set-your-location">
                                            </div>
                                    </div>
                                </div>
                                <b class="text">****   ****   ****   4576</b>
                                <img class="icons8-credit-card-64-1" alt="" src="/FormsIMG/icons8-credit-card-64 1.png">
                                
                        </div>
                    </div>
                    <div class="image-40">
                    </div>
                    <div class="fullform">
                        <div class="form1">
                                <div class="rectangleform">
                                </div>
                                <div class="how-do-you">How do you describe your style? (Select up to 3 options)</div>

                                <div class="form-option">
                                    <!--<li v-for="item in options">-->
                                    <li v-for="(item, index) in options" :key="index" class="checkbox-item">
                                        <input
                                            type="checkbox"
                                            :id="'checkbox-' + index"
                                            style="top: 18.52%; left: 1.5%; transform: scale(2.5);  left: 1.5%;; margin-top: 15px; margin-bottom: 15px;"
                                            :value="item"
                                            :disabled="disabledItems[index]"
                                            v-model="selectedOptions"
                                        />
                                        <label :for="'checkbox-' + index">{{ item }}</label>
                                    </li>
                                    <p> </p>
                                    <!-- <p>Selected: {{ selectedOptions.join(", ") }}</p> -->
                                </div>

                                    <!--
                                <div class="form-option9">
                                    <div class="outro">Other:</div>
                                    <div class="form-option-child"> <input type="text" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px; background-color: #f3f4f6;" onclick="limitCheckboxes()"></div>
                                </div>
                                    -->  
                        </div>

                        <div class="form2">
                                <div class="rectangleform">
                                </div>
                                <div class="linha4">
                                    <input type="text" v-model="pedidoInfo.fabricsPrefered" style="border: none; border-bottom: 1px solid #000; outline: none; width: 568px; font-size: 18px; background-color: #f3f4f6;">
                                </div>
                                <div class="q4">Do you prefer specific fabrics? (Ex.: cotton, linen, silk)</div>

                                <div class="linha3">
                                    <input type="text" v-model="pedidoInfo.pecasExcluidas" style="border: none; border-bottom: 1px solid #000; outline: none; width: 568px; font-size: 18px; background-color: #f3f4f6;">
                                </div>
                                <div class="q3">Is there any item of clothing you don't like wearing? (Ex.: skirt, tie, jeans)</div>

                                <div class="linha1">
                                    <input type="text" v-model="pedidoInfo.cores" style="border: none; border-bottom: 1px solid #000; outline: none; width: 568px; font-size: 18px; background-color: #f3f4f6;">
                                </div>
                                <div class="q11">What colors do you like to use the most?</div>
                        </div>


                        <div class="form3">
                                <div class="rectangleform">
                                    
                                </div>
                                <div class="what-is-the">What is the occasion for this combination?</div>

                                <div class="form3-child">
                                    <input type="text" v-model="pedidoInfo.nrOutfits" style="border: none; border-bottom: 1px solid #000; outline: none; width: 568px; font-size: 18px; background-color: #f3f4f6;">
                                </div>

                                <div class="how-many-outfits">How many outfits do you want to make?</div>



                                <div class="form-option10">
                                    <!--<li v-for="item in options">-->
                                    <li v-for="(item, index) in options2" :key="index" class="checkbox-item">
                                        <input
                                            type="checkbox"
                                            :id="'checkbox-' + index"
                                            style="top: 18.52%; left: 1.5%; transform: scale(2.5);  left: 1.5%;; margin-top: 15px; margin-bottom: 15px;"
                                            :value="item"
                                            :disabled="disabledItems2[index]"
                                            v-model="selectedOptions2"
                                        />
                                        <label :for="'checkbox-' + index">{{ item }}</label>
                                    </li>
                                    <p> </p>
                                    <!-- <p>Selected2: {{ selectedOptions2.join(", ") }}</p> -->
                                </div>

                                
                                <!--
                                <div class="form-option13">
                                    <div class="outro">Other:</div>
                                    <div class="form-option-child"> <input type="text" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px; background-color: #f3f4f6;" onclick="limitCheckboxes()"></div>
                                </div>
                                -->

                                
                                <div class="what-is-your">What is your budget for this outfit combination?</div>
                                <div class="form3-inner">
                                    <input type="text" v-model="pedidoInfo.orcamento" style="border: none; border-bottom: 1px solid #000; outline: none; width: 568px; font-size: 18px; background-color: #f3f4f6;">
                                </div>
                                
                                <!--
                                <div class="select-the-brands">Select the brands you want to receive recommendations for (optional):</div>
                                <div class="option1" id="option1Container">
                                    <div class="zara">ZARA</div>
                                </div>
                                <div class="option2" id="option2Container">
                                    <div class="primark">PRIMARK</div>
                                </div>
                                -->
                        </div>
                    </div>
            </div>
            
            
            <div v-if="showOverlay==true" id="form5Container" class="popup-overlay"> 
                    <div class="form5">
                        <div class="rectangleform4">
                        </div>
                        <div class="q12">
                                <p class="payment-made-successfully">New payment created successfully!</p>
                                <p class="payment-made-successfully">Go to your payments page</p>
                                <p class="payment-made-successfully">to conclude the process!</p>
                        </div>
                        <button class="nextbtn1" @click="confirmPurchase">Confirm Purchase</button>
                        
                    </div>
                      
            </div>

            <div v-if="showWarning==true" id="form5Container" class="popup-overlay"> 
                    <div class="form5">
                        <div class="rectangleform4">
                        </div>
                        <div class="q12">
                                <p class="payment-made-successfully">&emsp;&emsp;&emsp;Please finish filling the form!</p>
                        </div>
                        
                        <button class="nextbtn1" @click="closeWarningPopup">Return</button>
                        
                    </div>
                      
            </div>

            <!--
            <div v-if="showOverlay" class="overlay">
                <div class="overlay-content">
                    <h2>Payment Details</h2>
                    <button @click="showOverlay = false">Close</button>
                    
                </div>
            </div>

            
            <div id="option1selectedContainer" class="popup-overlay" style="display:none">
                    
                    <div class="option1selected">
                        <div class="zara1">ZARA</div>
                    </div>
                    
                    
            </div>
            
            <div id="option2selectedContainer" class="popup-overlay" style="display:none">
                    
                    <div class="option2selected">
                        <div class="primark1">PRIMARK</div>
                    </div>
                    
                    
            </div>
            -->
  	
        <FooterComponent></FooterComponent>
    </main>
</template>

<script>

import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import StylistIndividualView from '@/components/StylistIndividualView.vue';
import VueSelect from "vue3-select-component";
import Pedido from '@/models/pedido';
import Estilista from '@/models/estilista';
import authService from '@/services/auth-service';
import authHeader from '@/services/auth-header';
import axios from 'axios';



export default {

components:{
    NavBarComponent,
    FooterComponent,
    StylistIndividualView,
    VueSelect
},

data(){
    return{
        value: [0,1000],
        selectedOption:'',
        typeOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' }],
        items: [],
        filtered: [],
        current_page:0,
        showOverlay: false, // Inicialmente oculto
        showWarning: false, // Inicialmente oculto
        nomeEstilista:'' ,
        usernameEstilista:'',
        aux:'' ,

        // cenas do pedido (formulario)
        pedidoInfo: new Pedido('','','','','','','','',''),
        Estilista: new Estilista('','','','','',''),

        username:"",
        role:"",
        token:null,

        paymentType:"",

      options: ["Sportif", "Minimalist", "Streetwear", "Punk", "Romantic", "Vintage", "Classic", "Casual", "Boho"],
      selectedOptions: [], // Armazena as opções selecionadas

      options2: ["Work", "Formal event (e.g. wedding)", "Leisure/Travel", "Casual event (e.g. dinner)"],
      selectedOptions2: [], // Armazena as opções selecionadas

    }
},

created(){
		let token = authService.getToken();
		console.log(token);
		if(token!=null){
			this.token = token;
			this.username = token.username;
			this.role = token.role;
		}
},

computed: {
    // A computed property to handle disabling logic
    disabledItems() {
      return this.options.map(
        (item) => this.selectedOptions.length >= 3 && !this.selectedOptions.includes(item)
      );
    },
    disabledItems2() {
      return this.options2.map(
        (item) => this.selectedOptions2.length >= 3 && !this.selectedOptions2.includes(item)
      );
    },
},

mounted() {
    // Obtém o nome do estilista dos query params
    this.nomeEstilista = this.$route.query.stylistName;
    this.usernameEstilista = this.$route.query.stylistUsername;
    
},

methods:{
    toggleSelection(element) {
        if (element.classList.contains('selected')) {
            element.classList.remove('selected');
            selectedCount--;
        } else {
            if (selectedCount < maxSelections) {
                element.classList.add('selected');
                selectedCount++;
            } else {
                alert('Você pode selecionar apenas até 3 opções.');
            }
        }
    },



    checkIfPedidoIsFullyFilled(){
        console.log(
           "New pedido\n" +
           /* this.pedidoInfo.idPedido + ";\n" + */
           this.pedidoInfo.idCliente + ";\n" +
           this.pedidoInfo.estilos + ";\n" +
           this.pedidoInfo.cores + ";\n" +
           this.pedidoInfo.nrOutfits + ";\n" +
           this.pedidoInfo.orcamento + ";\n" +
           this.pedidoInfo.pecasExcluidas + ";\n" +
           this.pedidoInfo.fabricsPrefered + ";\n" +
           this.pedidoInfo.ocasioes + "\n"
        )

        if(/* this.pedidoInfo.idPedido != 0 && */
           this.pedidoInfo.idCliente != "" &&
           this.pedidoInfo.estilos != "" &&
           this.pedidoInfo.cores != "" &&
           this.pedidoInfo.nrOutfits != 0 &&
           this.pedidoInfo.orcamento != 0 &&
           this.pedidoInfo.pecasExcluidas != "" &&
           this.pedidoInfo.fabricsPrefered != "" &&
           this.pedidoInfo.ocasioes != "" ){
        
            return true;
        }

        else{
            return false;
        }
    },

    selectedOptionsString() {
      return this.selectedOptions.join(", "); // Convert the array to a comma-separated string
    },

    selectedOptions2String() {
      return this.selectedOptions2.join(", "); // Convert the array to a comma-separated string
    },

    showConfirmPopup() {
      if(this.paymentType != 0 && this.selectedOptions.length > 0 && this.selectedOptions2.length > 0){

        //this.pedidoInfo.idPedido = 1;   // 1 atua como placeholder
        this.pedidoInfo.idCliente = this.username;
        this.pedidoInfo.estilos = this.selectedOptionsString();
        this.pedidoInfo.ocasioes = this.selectedOptions2String();


        if(this.checkIfPedidoIsFullyFilled()){

            this.showOverlay = true;
        }
        else{
            this.showWarning = true;
        }
      }
      else{
        this.showWarning = true;
      }
    },
    

    closeWarningPopup() {
      this.showWarning = false;
    },
    async insertPedidoPending(codigo){
        const header = authHeader();
        let config = {headers:header}
        header['Content-Type'] = 'application/json';
        let itens = [];

        try{
            let r = await axios.post('http://localhost:8888/api/recomendacoesService/pedidos',
                {
                    "usernameCliente": this.username, 
                    "usernameEstilista": this.usernameEstilista,   
                    "nome":codigo, 
                    "estilos" : this.pedidoInfo.estilos,
                    "cores" : this.pedidoInfo.cores,
                    "nrOutfits" : this.pedidoInfo.nrOutfits,
                    "orcamento" : this.pedidoInfo.orcamento,
                    "peçasExcluidas" : this.pedidoInfo.pecasExcluidas,
                    "fabricsPreferences" : this.pedidoInfo.fabricsPrefered,
                    "occasions": this.pedidoInfo.ocasioes
                },
                config
            );
            console.log(r);
            return r;

        }catch(err){
            console.log(err);
            let msg="";
            if(err.response) msg = err.response.data.error.message;
            this.$swal.fire("Something went wrong! "+msg, "", "error");
        }
        
    },
    async confirmPurchase() {

        // criar o pagamento
        const header = authHeader();
        let config = {headers:header}
        header['Content-Type'] = 'application/json';
        
        let itens = [];
        
        let itensObj = {
            "itens" : itens
        }
        let dataGeracao = new Date();
        let codigo = "FORM"+this.username + Date.now();

        try{

            let r = await axios.post('http://localhost:8888/api/cartService/newpayment',
                {
                    "username":this.username,
                    "itensObj":itensObj,
                    "codigo":codigo,
                    "localEntrega":"",
                    "inicioAluguer":"",
                    "fimAluguer":"",
                    "modoPagamento":this.paymentType,
                    "dataGeracao": dataGeracao.toISOString(),
                    "total":7
                },
                config
            );
            console.log(r);
            if(r.status===200){
                let resp = await this.insertPedidoPending(codigo);
                console.log(resp);
            }
            this.$router.push({path:'/payments'})
            return r;

        }catch(err){
            console.log(err);
            let msg="";
            if(err.response) msg = err.response.data.error.message;
            this.$swal.fire("Something went wrong! "+msg, "", "error");
        }

        //this.$router.push({path:'/payments'})
    },

    changePaymentType(value) {
      this.paymentType = value;
    },

    handlePage(action){
        if(action=='previous' && this.current_page==0){
            this.showbtnprevious=false;
            return;
        }
        if(action=='previous' && this.current_page>0) {
            this.current_page-=1;
            this.showbtnnext=true;
            this.showbtnprevious=true;
            this.getStylists();
            return;
        }
        else {
            this.current_page+=1;
            this.showbtnprevious=true;
            this.getStylists();
        }
    }
},

}

</script>


<style lang="css" scoped>
@import '../assets/forms.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>

<style>

label {
  margin-left: 15px; /* Shift the label slightly to the left */
}

</style>