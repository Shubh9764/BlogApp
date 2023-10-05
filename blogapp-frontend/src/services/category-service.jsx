import axios from "axios";
import { myAxios } from "./helper";



export const loadAllCategories = () => {
    return axios.get("/api/categories").then((res) => res.data)
}