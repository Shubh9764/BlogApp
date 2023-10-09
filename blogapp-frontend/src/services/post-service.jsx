import { authAxios, getToken, getUser, myAxios } from "./helper";



export const createPost = (data) => authAxios.post(
    `/api/user/${getUser().id}/category/${data.categoryId}/posts`,data
).then((res) => {
   return res.data
}
)

export const getAllPosts = (pageNumber,pageSize) => myAxios.get(`/api/posts?pageNumber=${pageNumber}&pageSize=${pageSize}`).
then( (res) => {
    return res.data
})

export const getPostById=(postId) => myAxios.get(`/api/posts/${postId}`).then((res) => {
    return res.data
})

export const addComment = (postId,data) => {
    console.log(data)
    return authAxios.post(`/api/comments/user/${getUser().id}/post/${postId}`,data)
.then((res) => res.data)}

export const uploadImage = (postId,image) =>{
    let formData = new FormData()
    formData.append('image',image);
    return authAxios.post(`/api/post/image/upload/${postId}`,formData,{
        headers:{
            'Content-Type':`multipart/form-data`
        }
    })
    .then((res) => {
        return res.data
    })
}

export const getPostsByCategories = (categoryId) => {
    console.log("here")
    return myAxios.get(`/api/category/${categoryId}/posts`)
    .then((res) => {
        return res.data
    })
}