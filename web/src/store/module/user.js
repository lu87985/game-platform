import { login, logout} from '@/api/user'

export default {
  state: {
    userInfo: {
      userName: '',
      nickName: '',
      headImg: '',
      userId: '',
    },
    btn_status: false
  },
  mutations: {
    setUserInfo(state,payload) {
      state.userInfo = payload;
    },
    setBtnStatus(state,payload) {
      state.btn_status = payload;
    }
  },
  actions: {
    // 登录
    handleLogin({commit}, {username, password}) {
      username = username.trim();
      console.log(username);
      return new Promise((resolve, reject) => {
        login({
          username,
          password
        }).then(res => {
          if (res) {
              commit('setUserInfo',"");
              res && resolve()
          }else {
              commit('setBtnStatus',true)
          }
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 退出登录
    handleLogOut({ state, commit }) {
      return new Promise((resolve, reject) => {
        logout().then((res) => {
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    }
  }
}
