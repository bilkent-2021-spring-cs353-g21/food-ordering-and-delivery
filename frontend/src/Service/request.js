const SERVER_URL = "http://3.143.150.107:5000";
const request = async (method, url, params) => {
  url = SERVER_URL + url;
  try {
    var response;
    if (params) {
      response = await method(url, params, { withCredentials: true });
    } else {
      response = await method(url, { withCredentials: true });
    }
    return response;
  } catch (error) {
    if (error.response) {
      return error.response;
    } else {
      return {
        status: -1,
      };
    }
  }
};

export default request;
