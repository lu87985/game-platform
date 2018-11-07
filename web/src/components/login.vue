<template>
  <div class="login">
    <div class="login-content">
      <div class="leftCol">
        <div class="leftCol-box">
          <div class="loginBox-left">
            <h2>{{$t('login.title.login')}}</h2>
            <span>{{$t('login.title.info')}}</span>
            <div class="loginName">
              <span>{{$t('login.info.loginName')}}</span>
            </div>
            <input type="text" class="" v-model="username">
            <div class="loginName">
              <span>{{$t('login.info.password')}}</span>
            </div>
            <input type="password" class="" v-model="password">
          </div>
          <div class="loginBox-sep"></div>
          <div class="loginBox-right">
            <h2>{{$t('login.title.register')}}</h2>
            <span>{{$t('login.title.registerInfo')}}</span>
            <p>{{$t('login.info.register')}}</p>
          </div>
          <div class="loginBox-left but">
            <button class="login-button" @click="toLogin">{{$t('title.login')}}</button>
            <span v-if="!status">登录失败</span>
          </div>
          <div class="loginBox-right but">
            <a class="toRight"><span>{{$t('login.title.toRegister')}}</span></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { login } from "../api/user"
export default {
  name: 'Login',
  data(){
    return {
      username: '',
      password: '',
      status: this.$route.params.status
    }
  },
  methods: {
    toLogin(){
      const params = {
        username: this.username,
        password: this.password
      }
      login(params).then(res => {
        console.log(res);
        if (res.data.status) {
          this.$router.push('/helloWorld')
        } else {
          alert(res.data.data)
        }
      })
    }
  }
}
</script>
<style scoped>
.login {
    width: 100%;
    height: 100%;
    min-width: 972px;
    background-color: #173c57;
    position: relative;
    overflow: hidden;
  }
.login-content {
  width: 940px;
  margin: 0 auto;
  position: relative;
  min-height: 100%;
  font-family: Arial, Helvetica, sans-serif;
  color: #c6d4df;
  font-size: 12px;
}
.leftCol {
  width: 616px;
  float: left;
  margin-top: 40px;
  padding: 16px;
  background-color: rgba(0, 0, 0, 0.2);
}
.leftCol-box {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.loginName {
  margin-top: 20px;
}
.loginBox-left {
  float: left;
  position: relative;
  width: 274px;
  padding: 0px 16px 14px 0px;
  display: flex;
  flex-direction: column;
}
.loginBox-sep {
  left: 306px;
  border-left: 1px solid #38444e;
  width: 2px;
  height: 210px;
  margin: 0px;
  float: left;
}
.loginBox-right {
  float: left;
  position: relative;
  width: 274px;
  padding: 0px 0px 14px 16px;
}
.loginBox-right.but {
  padding-top: 20px;
}
.loginBox-right p{
  margin-top: 20px;
}
.login-button {
  width: 62px;
  height: 32px;
  border-radius: 2px;
  border: none;
  padding: 1px;
  margin-top: 20px;
  display: inline-block;
  cursor: pointer;
  font-size: 15px;
  text-decoration: none !important;
  color: #67c1f5 !important;
  background: rgba( 103, 193, 245, 0.2 );
}
.toRight {
  width: 109px;
  height: 32px;
  text-align: center;
  border-radius: 2px;
  border: none;
  padding: 1px;
  display: inline-block;
  cursor: pointer;
  text-decoration: none !important;
  color: #67c1f5 !important;
  background: rgba( 103, 193, 245, 0.2 );
}
.toRight span {
  padding: 0 15px;
  font-size: 15px;
  line-height: 32px;
}
</style>
