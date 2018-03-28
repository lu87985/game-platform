<template>
  <div class="homePage">
    <div class="home-page-content">
      <div class="store-header">
        <div class="store-nav">
          <div class="tab" >
            <span class="tab-span font-size-13">{{$t('title.recent')}}</span>
          </div>
          <div class="tab" >
            <span class="tab-span font-size-13">{{$t('title.game')}}</span>
          </div>
          <div class="tab">
            <span class="tab-span font-size-13">{{$t('title.software')}}</span>
          </div>
          <div class="tab">
            <span class="tab-span font-size-13">{{$t('title.video')}}</span>
          </div>
          <div class="store_search">
            <div class="searchBox">
              <input name="term" type="text" class="searchBox-input font-size-14" :placeholder="$t('placeholder.search')" size="22" autocomplete="off">
              <a href="#" class="searchBox-link">
                <img src="../assets/image/search.png">
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="home-page-content two">
      <h2>{{$t('title.recommend')}}</h2>
      <div class="carousel-container">
        <div class="carousel-items">
          <a class="item" :class="{'focus': index === isActive}" v-for="(val, index) in imgList">
            <img class="capsule" :src="val.img">
            <div class="info">
              <div class="app-name">
                <span>{{val.title}}</span>
              </div>
              <div class="app-content">
                <span class="font-size-14">{{val.content}}</span>
              </div>
              <div class="app-author">
                <span class="font-size-13">{{val.author}}</span>
              </div>
            </div>
          </a>
        </div>
        <div class="carousel-thumbs" data-usability="1">
          <div :class="{'focus': index === isActive}" v-for="(val, index) in imgList" :key="index" @click="carouselChange(index)"></div>
        </div>
        <div class="arrow left" @click="arrowLeft()">
          <span>←</span>
        </div>
        <div class="arrow right" @click="arrowRight()">
          <span>→</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import mahjong from '../assets/image/mahjong.jpg'
import plauCard from '../assets/image/play-card.jpg'
import PUBG from '../assets/image/pubg.jpg'

export default {
  name: 'Home',
  data: function () {
    return {
      isActive: 0,
      imgList: [
        {img: mahjong, title: this.$t('game.title.mahjong'), content: this.$t('game.content.mahjong'), author: this.$t('game.author.mahjong')},
        {img: plauCard, title: this.$t('game.title.playingCard'), content: this.$t('game.content.playingCard'), author: this.$t('game.author.playingCard')},
        {img: PUBG, title: this.$t('game.title.PUBG'), content: this.$t('game.content.PUBG'), author: this.$t('game.author.PUBG')}
      ]
    }
  },
  created: function () {
    setInterval(this.autoPlay, 3500);
  },
  methods: {
    autoPlay: function () {
      this.isActive++;
      if (this.isActive === this.imgList.length) { //当遍历到最后一张图片置零
        this.isActive = 0
      }
    },
    carouselChange: function (index) {
      console.log(index);
      this.isActive = index;
    },
    arrowRight: function () {
      const num = this.isActive++;
      if (num === this.imgList.length - 1) {
        this.isActive = 0;
      }
    },
    arrowLeft: function () {
      const num = this.isActive--;
      if (num == 0) {
        this.isActive = this.imgList.length - 1;
      }
    }
  }
}
</script>
<style scoped>
.homePage {
  width: 100%;
  height: 100%;
  min-width: 972px;
  background-color: #173c57;
  position: relative;
  overflow: hidden;
}
.home-page-content {
  position: relative;
  width: 940px;
  padding: 0 16px;
  margin: 0 auto;
}
.store-header {
  background-color: transparent;
  height: 66px;
  position: relative;
  width: 940px;
  margin: 0 auto;
  z-index: 300;
}
.store-nav {
  position: absolute;
  left: 0;
  right: 0;
  top: 24px;
  height: 35px;
  background: rgba( 62, 126, 167, 0.8);
  box-shadow: 0 0 3px rgba( 0, 0, 0, 0.4);
}
.store-nav .tab {
  border-right: 1px solid rgba( 16, 21, 25, 0.3);
  padding: 1px;
  display: inline-block;
  text-decoration: none;
  cursor: pointer;
}
.store-nav .tab:hover {
  background-color: #c7d5e0;
}
.store-nav .tab:hover .tab-span {
  color: #171A21;
}
.tab-span {
  padding-left: 20px;
  padding-right: 20px;
  background: none;
  color: #d9dadd;
  line-height: 34px;
  font-weight: 300;
}
.store_search {
  float: right;
  padding: 3px 4px 2px;
  height: 30px;
}
.searchBox {
  background: #316282 none;
  border-radius: 3px;
  border: 1px solid rgba( 0, 0, 0, 0.3);
  box-shadow: 1px 1px 0px rgba( 255, 255, 255, 0.2);
  color: #fff;
  margin-bottom: 0px;
  outline: none;
  height: 27px;
  padding: 0px 6px;
}
.searchBox input {
  border: none;
  background-color: transparent;
  color: #000000;
  margin-left: 5px;
  height: 25px;
  width: 180px;
  outline: none;
  padding-left: 5px;
  line-height: 26px;
}
.searchBox-input{
  color: #0e1c25;
  font-style: italic;
  margin-top: 1px;
  font-weight: 300;
}
.searchBox-link {
  position: absolute;
  right: 2px;
  top: 0;
  text-decoration: none;
  color: #ffffff;
}
.searchBox-link img {
  width: 25px;
  height: 25px;
  position: absolute;
  top: 5px;
  right: 5px;
}
.searchBox-link img:hover .searchBox{
  color: #ffffff;
}
.two {
  background: none;
  padding-top: 20px;
  padding-bottom: 40px;
  z-index: auto;
}
.carousel-container {
  position: relative;
  height: 380px;
  margin-top: 5px;
}
.carousel-items {
  position: relative;
  clear: both;
  height: 353px;
  overflow: hidden;
  box-shadow: 0 0 7px 0px #000;
}
.carousel-items .item {
  opacity: 0;
  pointer-events: none;
  transition: opacity 400ms;
  width: 940px;
  box-sizing: border-box;
  position: absolute;
  top: 0;
  left: 0;
  background: #171A21 right;
  color: #fff;
  display: flex;
}
.carousel-items .item .capsule {
  flex-shrink: 0;
  position: relative;
  z-index: 10002;
  margin-right: 10px;
  width: 616px;
  height: 353px;
  box-shadow: 0 0 10px 5px #000;
}
.carousel-items .item .info {
  flex-shrink: 1;
}
.carousel-items .item .info .app-name {
  display: flex;
  align-items: center;
  padding-top: 10px;
  font-weight: 300;
  height: 70px;
  padding-left: 6px;
  padding-right: 6px;
}
.carousel-items .item .info .app-name span {
  max-height: 52px;
  overflow: hidden;
  font-size: 24px;
  line-height: 35px;
}
.carousel-items .item .info .app-content {
  display: flex;
  align-items: center;
  padding-top: 10px;
  font-weight: 300;
  height: 70px;
  padding-left: 6px;
  padding-right: 6px;
}
.carousel-items .item .info .app-content span {
  max-height: 52px;
  overflow: hidden;
  line-height: 35px;
}
.carousel-items .item .info .app-author {
  display: flex;
  align-items: flex-end;
  padding-top: 10px;
  font-weight: 300;
  height: 70px;
  padding-left: 6px;
  padding-right: 6px;
  justify-content: flex-end;
}
.carousel-items .item .info .app-author span {
  max-height: 52px;
  overflow: hidden;
  line-height: 35px;
}
.carousel-thumbs {
  text-align: center;
  padding-bottom: 4px;
}
.carousel-thumbs div{
  display: inline-block;
  margin: 12px 2px;
  width: 15px;
  height: 9px;
  border-radius: 2px;
  transition: background-color 0.2s;
  background-color: hsla(202,60%,100%,0.2);
  cursor: pointer;
}
.carousel-items .focus {
  opacity: 1;
  pointer-events: auto;
}
.carousel-thumbs .focus {
  background-color: hsla(202,60%,100%,0.4);
}
.arrow {
  position: absolute;
  background-color: rgba(0,0,0,0.3);
  top: 35%;
  width: 23px;
  height: 36px;
  padding: 36px 11px;
  cursor: pointer;
  z-index: 2;
}
.arrow.left {
  left: -46px
}
.arrow.left span {
  width: 23px;
  line-height: 36px;
  color: #FFF;
}
.arrow.right {
  right: -46px;
}
.arrow.right span {
  width: 23px;
  line-height: 36px;
  color: #FFF;
}
@media (min-width: 1224px) {
  .home-page-content {
    padding-left: 234px;
  }
}
</style>
