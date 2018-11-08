import axios from 'axios'
import qs from 'qs'


const baseURL = "http://localhost:8081"


const $http = axios.create({
  baseURL: baseURL, // 因为我本地做了反向代理
  timeout: 10000,
  withCredentials: true, // 是否允许带cookie这些
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
  }
})


// POST传参序列化(添加请求拦截器)
$http.interceptors.request.use(
  config => {
    // 在发送请求之前做某件事
    if (
      config.method === 'post' ||
      config.method === 'put'
    ) {
      // 序列化
      config.data = qs.stringify(config.data)
    }

    // config.headers.common['authorization'] = Cookies.get('token');
    // if (String(config.url).indexOf('/tokens/captcha') != -1 || (String(config.url).indexOf('/tokens') != -1 && config.method === 'delete')) {
    //   delete config.headers.common['authorization']
    // }

    return config
  },
  error => {
    return Promise.reject(error.data)
  }
)


// 返回状态判断(添加响应拦截器)
$http.interceptors.response.use(
  res => {
    // if (res.data.code !== 10000) {
    // } else {
    //   return res.data.content;
    // }
    return res;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          //这里跳转登陆
          // Cookies.remove('token');
          window.location.href = "/login";
      }
    }
    return Promise.reject(error)
  }
)


export default $http
