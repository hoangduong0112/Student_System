import cookie from "react-cookies";

export const createHeaders = () => ({
    headers: {
        Authorization: 'Bearer ' + cookie.load('token'),
        'Cache-Control': 'no-cache'
    }
});
