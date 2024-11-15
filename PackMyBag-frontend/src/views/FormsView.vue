<template>
    <main>
        <NavBarComponent></NavBarComponent>
        
  	
  	
            <div class="formpage">
                    <img class="formpage-child" alt="" src="/SignUpIMG/Line 19.svg">
                    
                    <div class="send-mail-parent">
                        <div class="send-mail">
                                <div class="icon-send">
                                </div>
                        </div>
                        <div class="enter-your-email"> {{nomeEstilista}} </div>
                        <div class="avatar">
                                <img class="shape-icon" alt="" src="/FormsIMG/Shape.png">
                                
                        </div>
                    </div>
                    <div class="form4">
                        <div class="rectangleform">
                        </div>
                        <div class="nextbtn" id="nextBtnContainer">
                                <div class="nextbtn-child">
                                </div>

                                <!-- <div class="next">NEXT</div> -->
                                <button class="next" @click="gotToPayment()">NEXT</button>
                        </div>
                        <img class="image-36-icon" alt="" src="/FormsIMG/image 36.png">
                        
                        <img class="image-37-icon" alt="" src="/FormsIMG/image 37.png">
                        
                        <img class="image-38-icon" alt="" src="/FormsIMG/image 38.png">
                        
                        <div class="form4-child">
                            <input type="radio" name="paymethod" value="multibanco" style="transform: scale(2.5);" @click="changePaymentType(1)"/>
                        </div>
                        <div class="form4-item">
                            <input type="radio" name="paymethod" value="mbway" style="transform: scale(2.5);" @click="changePaymentType(2)"/>
                        </div>
                        <div class="form4-inner">
                            <input type="radio" name="paymethod" value="paypal" style="transform: scale(2.5);" @click="changePaymentType(3)"/>
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

                                <!--
                                <div class="form-option">
                                    <input class="esportivo" type="checkbox" name="question1" value="Sportif" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Sportif</div>
                                </div>

                
                                <div class="form-option1">
                                    <input class="esportivo" type="checkbox" name="question1" value="Minimalist" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Minimalist</div>
                                </div>
                                
                                <div class="form-option2">
                                    <input class="esportivo" type="checkbox" name="question1" value="Streetwear" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Streetwear</div>
                                </div>
                                <div class="form-option3">
                                    <input class="esportivo" type="checkbox" name="question1" value="Punk" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Punk</div>
                                </div>
                                <div class="form-option4">
                                    <input class="esportivo" type="checkbox" name="question1" value="Romantic" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Romantic</div>
                                </div>
                                <div class="form-option5">
                                    <input class="esportivo" type="checkbox" name="question1" value="Vintage" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Vintage</div>
                                </div>
                                <div class="form-option6">
                                    <input class="esportivo" type="checkbox" name="question1" value="Classic" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Classic</div>
                                </div>
                                <div class="form-option7">
                                    <input class="esportivo" type="checkbox" name="question1" value="Casual" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Casual</div>
                                </div>
                                <div class="form-option8">
                                    <input class="esportivo" type="checkbox" name="question1" value="Boho" onclick="limitCheckboxes()"/>
                                    <div class="form-option-child"> Boho</div>
                                </div>
                                <div class="form-option9">
                                    <div class="outro">Other:</div>
                                    <div class="form-option-child"> <input type="text" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px; background-color: #f3f4f6;" onclick="limitCheckboxes()"></div>
                                </div>
                                -->
                                <div class="form-option" v-for="(option, index) in options" :key="index">
                                <input
                                    type="checkbox"
                                    :value="option.value"
                                    v-model="selectedOptions"
                                    :disabled="selectedOptions.length >= 3 && !selectedOptions.includes(option.value)"
                                    @change="limitCheckboxes"
                                />
                                <div class="form-option-child">{{ option.label }}</div>
                                </div>
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
                                    <input class="esportivo" type="checkbox" name="question2" value="Work" onclick="limitCheckboxes2()" />
                                    <div class="form-option-child">Work</div>
                                </div>
                                

                                <div class="form-option11">
                                    <input class="esportivo" type="checkbox" name="question2" value="Formal event (e.g. wedding)" onclick="limitCheckboxes2()" />
                                    <div class="form-option-child">Formal event (e.g. wedding)</div>
                                </div>

                                <div class="form-option12">
                                    <input class="esportivo" type="checkbox" name="question2" value="Leisure/Travel" onclick="limitCheckboxes2()"/>
                                    <div class="form-option-child">Leisure/Travel</div>
                                </div>

                                <div class="form-option13">
                                    <div class="outro">Other:</div>
                                    <div class="form-option-child"> <input type="text" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px; background-color: #f3f4f6;" onclick="limitCheckboxes()"></div>
                                </div>

                                <div class="form-option14">

                                    <input class="esportivo" type="checkbox" name="question2" value="Casual event (e.g. dinner)" onclick="limitCheckboxes2()"/>
                                    <div class="form-option-child">Casual event (e.g. dinner)</div>
                                </div>

                                
                                <div class="what-is-your">What is your budget for this outfit combination?</div>
                                <div class="form3-inner">
                                    <input type="text" v-model="pedidoInfo.orcamento" style="border: none; border-bottom: 1px solid #000; outline: none; width: 568px; font-size: 18px; background-color: #f3f4f6;">
                                </div>
                                
                                <div class="select-the-brands">Select the brands you want to receive recommendations for (optional):</div>
                                <div class="option1" id="option1Container">
                                    <div class="zara">ZARA</div>
                                </div>
                                <div class="option2" id="option2Container">
                                    <div class="primark">PRIMARK</div>
                                </div>
                        </div>
                    </div>
            </div>
            
            
            <div v-if="showOverlay" id="form5Container" class="popup-overlay" style="display:none">
                    
                    <div class="form5">
                        <div class="rectangleform4">
                        </div>
                        <div class="q12">
                                <p class="payment-made-successfully">Payment made successfully!</p>
                                <p class="payment-made-successfully">You will hear back from the stylist soon!</p>
                        </div>
                        <div class="nextbtn1" id="popupnextBtnContainer">
                                <div class="nextbtn-item">
                                </div>
                                <div class="next1">RETURN</div>
                        </div>
                    </div>
                      
            </div>


            <div v-if="showOverlay" class="overlay">
                <div class="overlay-content">
                    <h2>Payment Details</h2>
                    <button @click="showOverlay = false">Close</button>
                    <!-- Adicione o conteúdo do overlay aqui -->
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
  	
  	
            
  	
        <FooterComponent></FooterComponent>
    </main>
</template>

<script>

import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import StylistIndividualView from '@/components/StylistIndividualView.vue';
import VueSelect from "vue3-select-component";
import Pedido from '@/models/pedido';

const maxSelections = 3;
let selectedCount = 0;

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
        nomeEstilista:'' ,

        // cenas do pedido (formulario)
        pedidoInfo: new Pedido('','','','','','','','',''),

        // cenas do formulário
        /*
        idPedido:Number,
        idCliente:String,
        estilos:String,
        cores:String,
        nrOutfits:Number,
        orcamento:Number,
        pecasExcluidas:String,
        fabricsPrefered:String,
        ocasioes:String,
        paymentType:0
        */

        options: [
        { value: "Sportif", label: "Sportif" },
        { value: "Minimalist", label: "Minimalist" },
        { value: "Streetwear", label: "Streetwear" },
        { value: "Punk", label: "Punk" },
        { value: "Romantic", label: "Romantic" },
        { value: "Vintage", label: "Vintage" },
        { value: "Classic", label: "Classic" },
        { value: "Casual", label: "Casual" },
        { value: "Boho", label: "Boho" },
      ],
      selectedOptions: [], // Armazena as opções selecionadas

    }
},

mounted() {
    // Obtém o nome do estilista dos query params
    this.nomeEstilista = this.$route.query.stylistName || "Guest";
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
        }
	},
    limitCheckboxes() {
      // Limitação já gerenciada pelo `v-model` e `:disabled`, mas você pode executar lógica adicional aqui se necessário.
    },
    changePaymentType(value) {
      this.paymentType = value;
      if (this.paymentType == 1){
        console.log("Paying with multibanco");
      }
      if (this.paymentType == 2){
        console.log("Paying with mbway");
      }
      if (this.paymentType == 3){
        console.log("Paying with paypal");
      }
      else{
        console.log("Payment not selected");
      }
    },
    goToPayment() {
        this.showOverlay = true;  // Exibe o overlay
    },
    getStylistWithUSername(){
        const header = authHeader();
        let config = {headers:header}
        header['Content-Type'] = 'application/json';

        axios.get('http://localhost:8888/api/utilizadoresService/estilistas/'+id).then(resp=>{
				let estilista = resp.data;
				this.nomeEstilista = estilista.nome;
			
				console.log(estilista);

			}).catch(err=>{
				console.log(err);
			})
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

}

</script>


<style lang="css" scoped>
@import '../assets/forms.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>