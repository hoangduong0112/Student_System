import cookie from 'react-cookies';


const removeCookie = () => {
    cookie.remove('token', { path: '/' });
    cookie.remove('user', { path: '/' });
}
const MyUserReducer = (currentState, action) => {
    switch (action.type) {
        case 'signin':
            return action.payload;

        case 'signout': {
            removeCookie()
            return null;
        }
    }
    return currentState;
}

export default MyUserReducer;