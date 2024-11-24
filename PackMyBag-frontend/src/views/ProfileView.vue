<template>
  <div>
    <NavBarComponent></NavBarComponent>
    <div class="profile-page">
      <div class="profile-container">
        <div class="profile-header">
          <div class="avatar" @click="triggerFileInput">
            <img :src="avatarUrl" alt="User Avatar" />
            <div class="avatar-overlay">Change</div>
          </div>
          <input type="file" ref="fileInput" @change="handleFileChange" class="file-input" />
          <button @click="saveAvatar" class="save-button">Save Avatar</button>
        </div>
  
        <div class="profile-info">

            <div class="profile-info-row">
            <label>Name:</label>
            <div class="field-container">
              <span v-if="!editMode['name']" class="field-value">{{ this.profile.name }}</span>
              <input 
                v-else 
                type="text" 
                v-model="profile['name']" 
                class="field-input"   
                @keyup.enter="toggleEditMode('name')"
              />
              <button @click="toggleEditMode('name')" class="edit-button">
                
              </button>
              <button 
                v-if="editMode['name']" 
                @click="saveField('name')" 
                class="save-button">
                Save
              </button>
            </div>
          </div>

            <div v-if="this.role==='Cliente'" class="profile-info-row">
            <label>Address:</label>
            <div class="field-container">
              <span v-if="!editMode['address']" class="field-value">{{ this.profile.address }}</span>
              <input 
                v-else 
                type="text" 
                v-model="profile['address']" 
                class="field-input"
                @keyup.enter="toggleEditMode('address')"
              />
              <button @click="toggleEditMode('address')" class="edit-button">
              </button>
              <button 
                v-if="editMode['address']" 
                @click="saveField('address')" 
                class="save-button">
                Save
              </button>
            </div>
            </div>

            <div v-if="this.role==='Cliente'" class="profile-info-row">
            <label>Phone:</label>
            <div class="field-container">
              <span v-if="!editMode['phone']" class="field-value">{{ this.profile.phone }}</span>
              <input 
                v-else 
                type="text" 
                v-model="profile['phone']" 
                class="field-input"
                @keyup.enter="toggleEditMode('phone')"
              />
              <button @click="toggleEditMode('phone')" class="edit-button">
              </button>
              <button 
                v-if="editMode['phone']" 
                @click="saveField('phone')" 
                class="save-button">
                Save
              </button>
            </div>
            </div>

            <div v-if="this.role==='Cliente'" class="profile-info-row">
            <label>Gender:</label>
            <div class="field-container">
              <span v-if="!editMode['gender']" class="field-value">{{ this.profile.gender }}</span>
              <input 
                v-else 
                type="text" 
                v-model="profile['gender']" 
                class="field-input"
                @keyup.enter="toggleEditMode('gender')"
              />
              <button @click="toggleEditMode('gender')" class="edit-button">
              </button>
              <button
              v-if="editMode['phone']" 
                @click="saveField('phone')" 
                class="save-button">
                Save
              </button>
            </div>
            </div>

            <div v-if="this.role==='Estilista'" class="profile-info-row">
            <label>Bio:</label>
            <div class="field-container">
              <span v-if="!editMode['bio']" class="field-value">{{ this.profile.bio }}</span>
              <input 
                v-else 
                type="text" 
                v-model="profile['bio']" 
                class="field-input"
                @keyup.enter="toggleEditMode('bio')"
              />
              <button @click="toggleEditMode('bio')" class="edit-button">
              </button>
              <button 
                v-if="editMode['bio']" 
                @click="saveField('bio')" 
                class="save-button">
                Save
              </button>
            </div>
            </div>




        </div>
      </div>
    </div>
    <FooterComponent></FooterComponent> 
  </div>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import authService from '@/services/auth-service';
import axios from 'axios';
export default {
    components:{
        NavBarComponent,
        FooterComponent
    },
    created(){
        let token = authService.getToken();
        if(token!==null){
            this.token = token;
            this.username = this.token.username;
            this.role = this.token.role;
			if(token.profileImage!=="") this.avatarUrl = "http://localhost:8888/api/utilizadoresService/profileImg/"+this.username;
            this.getUserInfo();
        }
        else this.$router.push({path:'/login'})
    },
    data() {
        return {
        avatarUrl: 'https://via.placeholder.com/150',
        newAvatarFile: null,
        role:'',
        profile: {
            name: '',
            address: '',
            phone: '',
            email: '',
            gender: '',
            bio: ''
        },
        token:null,
        editMode: {
            name: false,
            address: false,
            phone: false,
            gender: false,
            email: false,
            bio: false
        }
    };
  },
  methods: {
    formatLabel(field) {
      return field.charAt(0).toUpperCase() + field.slice(1);
    },
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
    handleFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.newAvatarFile = file;
        const reader = new FileReader();
        reader.onload = (e) => {
          this.avatarUrl = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
    saveAvatar() {
      if (this.newAvatarFile) {

        const form = new FormData();
        form.append('username', this.username);
        form.append('profile_image', this.newAvatarFile);

        axios.postForm('http://localhost:8888/api/utilizadoresService/updateImage',
            form
        ).then(resp=>{
            console.log(resp.data);
        }).catch(err=>{
            console.log(err);
        })
      } else {
        alert('No new avatar selected.');
      }
    },
    toggleEditMode(field) {
      this.editMode[field] = !this.editMode[field];
    },
    getUserInfo(){
        axios.get('http://localhost:8888/api/utilizadoresService/profileInfo/'+this.username)
        .then(resp=>{
            console.log(resp.data);
            if(this.role==="Cliente"){
                this.profile.name = resp.data.nome;
                this.profile.address = resp.data.morada;
                this.profile.phone = resp.data.nrTelemovel;
                this.profile.gender = resp.data.genero;
            }
        }).catch(err=>{
            console.log(err);
        })
    },
    saveField(field) {
      // Prepare the data to send as JSON
      const data = {
        username: this.username,
      };

      // Add the relevant field based on the role
      if (this.role === "Cliente") {
        if (field === "name") {
          data.newName = this.profile.name;
        } else if (field === "email") {
          data.newEmail = this.profile.email;
        } else if (field === "address") {
          data.newAddress = this.profile.address;
        } else if (field === "phone") {
          data.newPhone = this.profile.phone;
        } else if (field === "gender") {
          data.newGender = this.profile.gender;
        }
        console.log(data);
        // Send to the appropriate endpoint for Cliente
        axios.post('http://localhost:8888/api/utilizadoresService/updateProfile/normal', data, {
          headers: {
            'Content-Type': 'application/json',
            // Include any other necessary headers, such as authentication tokens
            'Authorization': `Bearer ${this.authToken}`
          }
        })
          .then((response) => {
            console.log(response.data);
            this.editMode[field] = false;  // Exit edit mode for the field
            alert('Field updated successfully!');
          })
          .catch((error) => {
            console.error(error);
            alert('Failed to update the field.');
          });

      } else if (this.role === "Estilista") {
        if (field === "name") {
          data.newName = this.profile.name;
        } else if (field === "email") {
          data.newEmail = this.profile.email;
        } else if (field === "bio") {
          data.newBio = this.profile.bio;
        } else if (field === "gender") {
          data.newGender = this.profile.gender;
        }

        // Send to the appropriate endpoint for Estilista
        axios.post('http://localhost:8888/api/utilizadoresService/updateProfile/estilista', data, {
          headers: {
            'Content-Type': 'application/json'  // Sending JSON data
          }
        })
          .then((response) => {
            console.log(response.data);
            this.editMode[field] = false;  // Exit edit mode for the field
            alert('Field updated successfully!');
          })
          .catch((error) => {
            console.error(error);
            alert('Failed to update the field.');
          });

      } else if (this.role === "Tecnico") {
        if (field === "name") {
          data.newName = this.profile.name;
        } else if (field === "email") {
          data.newEmail = this.profile.email;
        } else if (field === "department") {
          data.newDepartment = this.profile.department;
        } else if (field === "gender") {
          data.newGender = this.profile.gender;
        }

        // Send to the appropriate endpoint for Tecnico
        axios.post('http://localhost:8888/api/utilizadoresService/updateProfile/tecnico', data, {
          headers: {
            'Content-Type': 'application/json'  // Sending JSON data
          }
        })
          .then((response) => {
            console.log(response.data);
            this.editMode[field] = false;  // Exit edit mode for the field
            alert('Field updated successfully!');
          })
          .catch((error) => {
            console.error(error);
            alert('Failed to update the field.');
          });

      } else {
        alert("Invalid role or field.");
        return;
      }
    },
  },
  
};
</script>

<style lang="css" scoped>
@import '../assets/profile.css';
</style>