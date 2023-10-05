import { authAxios, getUser, myAxios } from "./helper";



export const createPost = (data) => authAxios.post(
    `/api/user/${getUser().id}/category/${data.categoryId}/posts`,data
).then((res) => {
   return res.data
}
)