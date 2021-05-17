import axios from "axios";
import request from "./request";

export const getMeals = async (rid) => {
    const response = await request(axios.get, "/restaurant/" + rid + "/meals");
    if (response.status !== 200) {
        throw response;
    }

    return response;
};

export const addToBasket = async (data) => {
    const response = await request(axios.put, "/customer/basket", data);
    if (response.status !== 200) {
        throw response;
    }

    return response;
};

export const getBasket = async () => {
    const response = await request(axios.get, "/customer/basket");
    if (response.status !== 200) {
        throw response;
    }

    return response;
};

export const search = async (data) => {
    const response = await request(axios.post, "/search", data);
    if (response.status !== 200) {
        throw response;
    }

    return response;
};
