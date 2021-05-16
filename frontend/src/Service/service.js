import axios from "axios";
import request from "./request";

export const search = async (data) => {
    const response = await request(axios.post, "/search", data);
    if (response.status !== 200) {
        throw response;
    }

    return response;
};
