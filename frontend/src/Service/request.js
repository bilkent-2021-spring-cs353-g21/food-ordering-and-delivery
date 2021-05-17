import axios from "axios";

const SERVER_URL = "https://52.59.101.158/api";
const request = async (method, url, params) => {
    url = SERVER_URL + url;
    axios.defaults.withCredentials = true;
    try {
        var response;
        if (params) {
            response = await method(url, params, { withCredentials: true });
        } else {
            response = await method(url, { withCredentials: true });
        }
        return {
            status: response.status,
            data: response.data,
        };
    } catch (error) {
        if (error.response) {
            return {
                status: error.response.status,
                data: error.response.data,
            };
        } else {
            return {
                status: 503,
                data: {},
            };
        }
    }
};
export default request;
