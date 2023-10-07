import axios from 'axios'

export const BASE_URL ="http://localhost:8080";

axios.defaults.baseURL = BASE_URL;

axios.defaults.headers.post['Content-Type'] = 'application/json' 



export const myAxios = axios.create({
    baseURL:BASE_URL
})

export const isLoggedIn = () => {
    return localStorage.getItem("data")?true:false
}

export const doLogin = (token,next) =>{
    localStorage.setItem("data",JSON.stringify(token))
    next()
}
export const doLogout = (next) =>{
    localStorage.removeItem("data")
    next()
}

export const getUser = () => {
    if(isLoggedIn)return JSON.parse(localStorage.getItem("data"))?.user;
    return null;
}

export const getToken = () => {
    if(isLoggedIn)return JSON.parse(localStorage.getItem("data"))?.token;
    return null;
}

export const authAxios = axios.create({
    baseURL:BASE_URL,
    headers:{Authorization:`Bearer ${getToken()}`}
})

// authAxios.interceptors.request.use(config => {
//     const token = getToken()

//     if(token){
//         config.headers.Authorization=`Bearer ${token}`
//         return config
//     }
// },error => Promise.reject(error))
