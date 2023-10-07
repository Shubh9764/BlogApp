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