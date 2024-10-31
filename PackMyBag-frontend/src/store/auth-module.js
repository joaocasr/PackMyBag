import authService from "@/services/auth-service";

const user = JSON.parse(localStorage.getItem('user'));

const initialState = user ? {status:{loggedIn:true},user:user}: {status:{loggedIn:false},user:null};

export const auth = {
    namespaced: true,
    state: initialState,
    actions: {
      login({ commit }, user) {
        return authService.login(user).then(
          response => {
            commit('loginSuccess', response);
            return Promise.resolve(response.data);
          },
          error => {
            commit('loginFailure');
            return Promise.reject(error);
          }
        );
      },
      logout({ commit }) {
        authService.logout();
        commit('logout');
      },
      signUpUser({ commit }, user) {
        return authService.signUpUser(user).then(
          response => {
            commit('registerSuccess');
            return Promise.resolve(response.data);
          },
          error => {
            commit('registerFailure');
            return Promise.reject(error);
          }
        );
      }
    },
    mutations: {
      loginSuccess(state, user) {
        state.status.loggedIn = true;
        state.user = user;
      },
      loginFailure(state) {
        state.status.loggedIn = false;
        state.user = null;
      },
      logout(state) {
        state.status.loggedIn = false;
        state.user = null;
      },
      registerSuccess(state) {
        state.status.loggedIn = false;
      },
      registerFailure(state) {
        state.status.loggedIn = false;
      }
    }
  };