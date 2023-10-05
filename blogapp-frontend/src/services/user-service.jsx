import { myAxios } from "./helper"


export const signup = (user) => {
    console.log(user)
    return myAxios.post("/api/auth/register",user).then((res) => res.data)
}

export const login = (user) => {
    console.log(user)
    return myAxios.post("/api/auth/login",user).then((res) => res.data)
}