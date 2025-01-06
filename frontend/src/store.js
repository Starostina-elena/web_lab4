import { createStore } from 'vuex';

export default createStore({
    state: {
        username: '',
        password: '',
        creatorId: null
    },
    mutations: {
        setUser(state, { username, password, creatorId }) {
            state.username = username;
            state.password = password;
            state.creatorId = creatorId;
        },
        clearUser(state) {
            state.username = '';
            state.password = '';
            state.creatorId = null;
        }
    },
    actions: {
        saveUser({ commit }, user) {
            commit('setUser', user);
        },
        clearUser({ commit }) {
            commit('clearUser');
        }
    },
    getters: {
        getUsername: state => state.username,
        getPassword: state => state.password,
        getCreatorId: state => state.creatorId
    }
});