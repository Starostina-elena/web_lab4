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
        }
    },
    actions: {
        saveUser({ commit }, user) {
            commit('setUser', user);
        }
    },
    getters: {
        getUsername: state => state.username,
        getPassword: state => state.password,
        getCreatorId: state => state.creatorId
    }
});