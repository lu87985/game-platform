<template>
  <div class="main">
    <div class="main_header">
      <div class="content">
        <div class="logo">
          <a href="javascript:;">
            <img src="../assets/image/logo.png" width="176" height="44">
          </a>
        </div>
        <div class="main-container">
          <a class="menuItem font-size-14">{{$t('title.store')}}</a>
          <a class="menuItem font-size-14">{{$t('title.game')}}</a>
          <a class="menuItem font-size-14">{{$t('title.about')}}</a>
          <a class="menuItem font-size-14">{{$t('title.support')}}</a>
        </div>
        <div class="action">
          <div class="action-menu">
            <a class="action-link">{{$t('title.login')}}</a>
            &nbsp;|&nbsp;
            <select class="language" name="language" @change="clickLanguage" v-model="locale">
              <option value="en">English({{$t('language.en')}})</option>
              <option value="zh_TW">繁體中文({{$t('language.zh_TW')}})</option>
              <option value="zh_CN">简体中文({{$t('language.zh_CN')}})</option>
              <option value="ja">Japanese({{$t('language.ja')}})</option>
              <option value="ko">한국어({{$t('language.ko')}})</option>
            </select>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Main',
    data: function () {
      return {
        locale: ''
      }
    },
    mounted: function () {
      this.locale = this.$i18n.locale;
    },
    methods: {
      clickLanguage: function () {
        this.$i18n.locale = this.locale;
      }
    }
  }
</script>

<style scoped>
  .main_header {
    min-width: 940px;
    min-height: 105px;
    background: #171a21;
    font-family: SansSerif;
    font-weight: normal;
    font-size: 14px;
    padding-left: 16px;
    padding-right: 16px;
  }
  .content {
    position: relative;
    width: 940px;
    height: 105px;
    margin: 0px auto;
    z-index: 401;
  }
  .logo {
    float: left;
    padding-top: 30px;
    margin-right: 40px;
    width: 176px;
    height: 44px;
  }
  .main-container {
    position: absolute;
    left: 200px;
  }
  .menuItem {
    display: block;
    position: relative;
    padding: 45px 7px 7px 7px;
    line-height: 16px;
    float: left;
    color: #b8b6b4;
    text-decoration: none;
    cursor:pointer;
  }
  .menuItem:hover {
    color: #ffffff;
  }
  .action {
    position: absolute;
    right: 0px;
    top: 6px;
    height: 21px;
    line-height: 21px;
    color: #b8b6b4;
    font-size: 11px;
    z-index: 400;
  }
  .action-menu {
    line-height: 24px;
    vertical-align: top;
    display: inline-block;
  }
  .action-link {
    color: #b8b6b4;
    padding: 0 4px;
    cursor: pointer;
  }
  .action-link:hover {
    color: #ffffff;
  }
  .language {
    width: 80px;
    height: 20px;
    margin: 0px;
    border: 1px solid #82807c;
    font-size: 11px;
    background: #171a21;
    color: #ffffff;
  }
</style>
